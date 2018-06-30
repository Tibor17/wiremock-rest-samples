/**
 * Copyright (c) 2014 Scheidt & Bachmann, Inc.. All Rights Reserved.
 */
package com.scheidtbachmann.client;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author <a href="mailto:digana.tibor@scheidt-bachmann.sk">digana.tibor (Tibor Digana)</a>
 * @since 1.0
 */
public final class ResolvedResource {
    private final URL address;
    private final String user;
    private final String password;

    public ResolvedResource(String address, String user, String password) throws MalformedURLException {
        this.address = new URL(address);
        this.user = user;
        this.password = password;
    }

    public URL getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean hasCredentials() {
        return getAddress() != null && getPassword() != null;
    }
}