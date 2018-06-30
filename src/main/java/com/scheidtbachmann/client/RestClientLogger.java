package com.scheidtbachmann.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * REST client logger.
 */
@Provider
public class RestClientLogger
        implements ClientResponseFilter, Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(RestClientLogger.class);

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        StringBuilder builder = new StringBuilder()
                .append("REQUEST >> ")
                .append(requestContext.getMethod())
                .append(' ')
                .append(requestContext.getUri().getPath())
                .append(' ')
                .append("HTTP/1.1")
                .append('\n')
                .append("RESPONSE << ")
                .append("HTTP/1.1")
                .append(' ')
                .append(responseContext.getStatusInfo().getStatusCode())
                .append(' ')
                .append(responseContext.getStatusInfo().getReasonPhrase())
                .append('\n');

        MultivaluedMap<String, String> headers = responseContext.getHeaders();

        printHttpHeaders(builder, headers);

        BufferedInputStream is = new BufferedInputStream(responseContext.getEntityStream());
        is.mark(Integer.MAX_VALUE);

        printHttpBody(builder, is);

        is.reset();
        responseContext.setEntityStream(is);

        LOG.info(builder.toString());
    }

    private static void printHttpBody(StringBuilder builder, BufferedInputStream is) {
        for (Scanner scanner = new Scanner(is); scanner.hasNextLine(); ) {
            builder.append(scanner.nextLine())
                    .append('\n');
        }
    }

    private static void printHttpHeaders(StringBuilder builder, MultivaluedMap<String, String> headers) {
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            builder.append(header.getKey())
                    .append(": ");
            for (String value : header.getValue()) {
                builder.append(value).append(", ");
            }
            builder.append('\n');
        }
    }
}