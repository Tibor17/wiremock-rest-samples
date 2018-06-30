package com.scheidtbachmann.client;

import com.github.tomakehurst.wiremock.client.BasicCredentials;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.scheidtbachmann.invoice.DocumentDataType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXB;
import java.io.StringWriter;
import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.util.Locale.GERMAN;
import static javax.ws.rs.core.HttpHeaders.*;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpMockBasicAuthenticationTest {
    private static final String APP_ROOT = "/reportws/rest/reportExecutions";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig()
            .dynamicPort()
            .needClientAuth(true)
    );

    @Before
    public void resetServer() {
        resetToDefault();
        wireMockRule.addMockServiceRequestListener((request, response) -> {
            System.out.println("=== REQUEST ===");
            System.out.println(request.getMethod().value() + " " + request.getUrl() + " HTTP/1.1");
            request.getHeaders().all().forEach(System.out::println);
            String body = request.getBodyAsString().trim();
            if (!body.isEmpty()) {
                System.out.println(body);
            }

            System.out.println("=== RESPONSE ===");
            System.out.println("HTTP/1.1 " + response.getStatus());
            response.getHeaders().all().forEach(System.out::println);
            System.out.println(response.getBodyAsString());
        });
    }

    @Test
    public void shouldPostPDF() throws Exception {
        DocumentDataType actual = new DocumentBuilder().build();
        StringWriter writer = new StringWriter();
        JAXB.marshal(actual, writer);

        URI appRoot = new URI("http", null, "localhost", wireMockRule.port(), APP_ROOT, null, null);
        URI uri = UriBuilder.fromUri(appRoot)
                .path("tenants/{tenant}/reports/{report}/main/{template}")
                .queryParam("outputFormat", "pdf")
                .build("default", "invoicing", "001_invoice", "pdf");
        serverMockWithWireMock(uri);
        JRSAdapterClientService s = new JRSAdapterClientService();
        s.initAsTest(new URI("http", "username:pswd", "localhost", wireMockRule.port(), APP_ROOT, null, null).toURL(), null, null);
        byte[] pdf = s.doGetInvoicePdf("default", "invoicing", "001_invoice", GERMAN, actual);
        assertThat(pdf).isEqualTo(new byte[] {1, 2, 3});

        serverVerificationWithWireMock();
    }

    private static void serverVerificationWithWireMock() {
        verify(postRequestedFor(urlPathMatching("/reportws/rest/reportExecutions/tenants/default/reports/invoicing/main/001_invoice"))
                        .withBasicAuth(new BasicCredentials("username", "pswd"))
                        .withQueryParam("outputFormat", equalTo("pdf"))
                        .withRequestBody(matching(".*<documentData>.*</documentData>"))
                        .withRequestBody(matchingXPath("/documentData/document[customizationToken = 'some operator hash']"))
                        .withRequestBody(matchingXPath("/documentData/contract/invoiceBody/orderedItems[count(orderedItem) = 1]"))
                        .withRequestBody(matchingXPath("/documentData/contract/invoiceBody/orderedItems/orderedItem/positions[count(position) = 1]"))
                        .withRequestBody(matchingXPath("/documentData/contract/invoiceBody/orderedItems/orderedItem/positions/position[1][@singleOrderNettPrice = '3']"))
                        .withRequestBody(matchingXPath("//parent::positions/position[1][@ordersCount = 2]"))
                        .withRequestBody(matchingXPath("//parent::positions/position[1][@totalNettPrice = 6]"))
                        .withRequestBody(matchingXPath("//parent::positions/position[1][@vat = 19]"))
                        .withRequestBody(matchingXPath("//parent::positions/position[1][@totalTaxPrice = '1.2']"))
                        .withRequestBody(matchingXPath("//parent::positions/position[1][@totalGrossPrice = '7.2']"))
                        .withRequestBody(matchingXPath("//parent::positions/position[1][@currencyCode = 'EUR']"))
                        .withRequestBody(matchingXPath("//parent::positions/position[1][text() = 'some product']"))
                        .withRequestBody(matchingXPath("//parent::position[@singleOrderNettPrice = '3' and text() = 'some product']"))
                        .withHeader(CONTENT_LENGTH, matching("1496"))
                        .withHeader(CONTENT_TYPE, containing(APPLICATION_XML))
                        .withHeader(ACCEPT_ENCODING, equalTo("identity"))
        );
    }

    private void serverMockWithWireMock(URI uri) {
        // See the URL escape characters http://www.w3schools.com/tags/ref_urlencode.asp
        givenThat(post(urlPathMatching(uri.getPath()))
                        .withBasicAuth("username", "pswd")
                        .withHeader(CONTENT_TYPE, equalTo(APPLICATION_XML))
                        .withHeader(ACCEPT_LANGUAGE, equalTo("de"))
                        .withQueryParam("outputFormat", equalTo("pdf"))
                        .willReturn(aResponse()
                                .withHeader(LOCATION, "/reportws/rest/reportExecutions/tenants/default/reports/6716046")
                                .withHeader(CONTENT_TYPE, "application/pdf")
                                .withBody(new byte[]{1, 2, 3})
                                .withStatus(CREATED.getStatusCode()))
        );
    }
}
