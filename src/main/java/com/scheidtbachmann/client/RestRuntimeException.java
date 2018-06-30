package com.scheidtbachmann.client;

import javax.ws.rs.core.Response.StatusType;

public abstract class RestRuntimeException
        extends RuntimeException {

    protected RestRuntimeException(StatusType error) {
        super("HTTP " + error.getStatusCode() + ' ' + error.getReasonPhrase());
    }
}
