package com.scheidtbachmann.client;

import javax.ws.rs.core.Response.StatusType;

public final class RestServerRuntimeException
        extends RestRuntimeException {

    public RestServerRuntimeException(StatusType error) {
        super(error);
    }
}
