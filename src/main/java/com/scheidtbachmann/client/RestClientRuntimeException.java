package com.scheidtbachmann.client;

import javax.ws.rs.core.Response.StatusType;

public final class RestClientRuntimeException
        extends RestRuntimeException {

    public RestClientRuntimeException(StatusType error) {
        super(error);
    }
}
