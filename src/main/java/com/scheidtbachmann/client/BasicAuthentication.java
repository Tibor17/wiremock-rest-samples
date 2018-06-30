package com.scheidtbachmann.client;

import javax.annotation.Priority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static javax.ws.rs.Priorities.AUTHENTICATION;

@Provider
@Priority(AUTHENTICATION)
public class BasicAuthentication
        implements ClientRequestFilter {
    private static final String AUTHENTICATION_SCHEME = "Basic";

    private final String authorization;

    BasicAuthentication(String username, String password) {
        authorization = username + ':' + password;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        String authHeader = AUTHENTICATION_SCHEME
                + " "
                + Base64.getEncoder().encodeToString(authorization.getBytes(US_ASCII));

        requestContext.getHeaders()
                .putSingle(HttpHeaders.AUTHORIZATION, authHeader);
    }
}
