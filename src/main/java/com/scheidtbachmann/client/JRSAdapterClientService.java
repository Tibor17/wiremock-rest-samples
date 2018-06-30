package com.scheidtbachmann.client;

import com.scheidtbachmann.invoice.DocumentDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.*;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.Variant;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.lang.Character.isWhitespace;
import static java.util.Collections.emptyMap;
import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.HttpHeaders.WWW_AUTHENTICATE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static javax.ws.rs.core.Response.Status.*;

@ApplicationScoped
public class JRSAdapterClientService {
    private static final Logger LOG = LoggerFactory.getLogger(JRSAdapterClientService.class);

    /**
     * /subsystem=naming/binding=java\:global\/jrs\/connection\/rest\/root/:add(binding-type=simple, type=java.net.URL, cache=false, value="https://10.46.26.20:8443/reportws/rest/reportExecutions")
     */
    @Resource(name = "java:global/jrs/connection/rest/root")
    private URL connection;

    @Resource(name = "java:global/jrs/connection/rest/ssl/keystore/file")
    private File restServerKeystoreFile;

    @Resource(name = "java:global/jrs/connection/rest/ssl/keystore/storepass")
    private String restServerKeystoreStorepass;

    private volatile Client client;

    @PostConstruct
    private void init() {
        String userInfo = connection.getUserInfo();
        BasicAuthentication filter = null;
        if (userInfo != null) {
            String[] credentials = userInfo.split(":");
            filter = new BasicAuthentication(credentials[0], credentials.length == 1 ? null : credentials[1]);
        }

        try {
            client = createClient(restServerKeystoreFile, restServerKeystoreStorepass, filter);
        } catch (IOException | GeneralSecurityException e) {
            LOG.error(e.getLocalizedMessage(), e);
            // let's fail later on NPE. Cannot use default client.
        }
    }

    void initAsTest(URL connection, File keystoreFile, String storepass) {
        this.connection = connection;
        restServerKeystoreFile = keystoreFile;
        restServerKeystoreStorepass = storepass;
        init();
    }

    @PreDestroy
    void releaseResources() {
        client.close();
    }

    public byte[] doGetInvoicePdf(String tenant, String report, String template, Locale language, DocumentDataType inv) {
        Response response = null;
        try {
            WebTarget webTarget = client.target(toUriWithoutUserInfo(connection))
                    .path("tenants/{tenant}/reports/{report}/main/{template}")
                    .resolveTemplate("tenant", tenant)
                    .resolveTemplate("report", report)
                    .resolveTemplate("template", template)
                    .queryParam("outputFormat", "pdf");

            URI constructedUri = webTarget.getUri();

            response = webTarget.request()
                    .acceptLanguage(language)
                    .acceptEncoding("identity")
                    .post(entity(inv, new Variant(APPLICATION_XML_TYPE, (Locale) null, "UTF-8")));

            StatusType stat = response.getStatusInfo();
            Family family = stat.getFamily();
            LOG.info("{}: HTTP {} {}"
                            + "\n"
                            + "POST {} HTTP/1.1"
                            + "\n"
                            + HttpHeaders.LOCATION + ": {}",
                    family, stat.getStatusCode(), stat.getReasonPhrase(), constructedUri.getPath(), response.getLocation());

            switch (family) {
                case SUCCESSFUL:
                    Status responseStatus = fromStatusCode(stat.getStatusCode());
                    if (responseStatus == CREATED) {
                        return response.hasEntity() ? response.readEntity(byte[].class) : null;
                    } else if (responseStatus == NO_CONTENT) {
                        LOG.warn("No Content, returning NULL.");
                        return null;
                    } else {
                        logResponseEntity(response);
                        throw new RestCannotProcessRuntimeException(stat);
                    }
                case CLIENT_ERROR:
                    logResponseEntity(response);
                    throw new RestClientRuntimeException(stat);
                case SERVER_ERROR:
                    logResponseEntity(response);
                    throw new RestServerRuntimeException(stat);
                default:
                    logResponseEntity(response);
                    throw new RestCannotProcessRuntimeException(stat);
            }
        } catch (URISyntaxException e) {
            LOG.error(e.getLocalizedMessage(), e);
            throw new IllegalStateException(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private static URI toUriWithoutUserInfo(URL connection) throws URISyntaxException {
        String protocol = connection.getProtocol();
        String host = connection.getHost();
        int port = connection.getPort();
        String path = connection.getPath();
        return new URI(protocol, null, host, port, path, null, null);
    }

    private static void logResponseEntity(Response response) {
        if (response.hasEntity()) {
            Object e = response.getEntity();
            Response.StatusType stat = response.getStatusInfo();
            LOG.error("HTTP/1.1 " + stat.getStatusCode() + " " + stat.getReasonPhrase()
                    + (e == null ? "" : ("\n" + e)));

            if (fromStatusCode(stat.getStatusCode()) == UNAUTHORIZED) {
                String authServerRequest = response.getHeaderString(WWW_AUTHENTICATE);
                LOG.error("Unauthorized access. See the HTTP Header ({}: {}).", WWW_AUTHENTICATE, authServerRequest);
                WwwAuthenticateParser parser = parseAuthenticationHeader(authServerRequest);
                Map<String, String> params = parser.getParameters();
                LOG.error("Unauthorized access with parsed realm {} and parsed charset {}. "
                                + "The parser should be used in the second call of rest client.",
                        params.get("realm"), params.get("charset"));
            }
        }
    }

    private static Client createClient(File keyStore, String storePass, BasicAuthentication filter)
            throws IOException, GeneralSecurityException {
        ClientBuilder clientBuilder =
                ClientBuilder.newBuilder()
                        .register(RestClientLogger.class);

        if (keyStore != null && storePass != null) {
            SSLContext sslContext = createSSLContext(keyStore, storePass);
            clientBuilder.sslContext(sslContext)
                    .hostnameVerifier(getHostnameVerifier());
        }

        if (filter != null) {
            clientBuilder.register(filter);
        }

        return clientBuilder.build();
    }

    private static HostnameVerifier getHostnameVerifier() {
        return (hostname, sslSession) -> {
            LOG.info(hostname
                    + " creation-time: " + String.format("%,d", sslSession.getCreationTime())
                    + ", last-accessed-time: " + String.format("%,d", sslSession.getLastAccessedTime()));
            return true;
        };
    }

    private static SSLContext createSSLContext(File keyStore, String storePass) throws IOException, GeneralSecurityException {
        TrustManager[] tm = {new KeystoreTrustManager(keyStore, storePass.toCharArray())};
        KeyManager[] km = {new KeystoreManager(keyStore, storePass.toCharArray())};
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(km, tm, null);
        return ctx;
    }

    static WwwAuthenticateParser parseAuthenticationHeader(String authentication) {
        return authentication == null ? new WwwAuthenticateParser(false, emptyMap()) : parseAuthResponse(authentication.trim());
    }

    private static WwwAuthenticateParser parseAuthResponse(String authentication) {
        int i = 0;
        if (authentication.length() > 5
                && authentication.charAt(i++) == 'B'
                && authentication.charAt(i++) == 'a'
                && authentication.charAt(i++) == 's'
                && authentication.charAt(i++) == 'i'
                && authentication.charAt(i++) == 'c') {
            Map<String, String> parameters = new HashMap<>();
            String key = "", value = "";
            boolean isKey = true, isValueStarted = false, isValueFinished = false;
            while (i < authentication.length()) {
                char c = authentication.charAt(i++);
                if (isKey) {
                    if (c == '=') {
                        isKey = false;
                    } else if (!isWhitespace(c)) {
                        key += c;
                    }
                    continue;
                }

                if (c == '"') {
                    if (!isValueStarted) {
                        isValueStarted = true;
                        continue;
                    } else {
                        isValueFinished = true;
                    }
                }

                if (isValueStarted) {
                    if (isValueFinished) {
                        parameters.put(key.trim(), value);
                        isValueStarted = isValueFinished = false;
                        isKey = true;
                        key = value = "";
                    } else {
                        value += c;
                    }
                }
            }
            return new WwwAuthenticateParser(true, parameters);
        }
        return new WwwAuthenticateParser(false, emptyMap());
    }
}
