package com.scheidtbachmann.client;

import javax.ws.rs.core.Response.StatusType;

public final class RestCannotProcessRuntimeException
        extends RestRuntimeException {

    public RestCannotProcessRuntimeException(StatusType error) {
        super(error);
    }
}
