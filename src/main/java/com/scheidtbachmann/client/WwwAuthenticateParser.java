package com.scheidtbachmann.client;

import java.util.HashMap;
import java.util.Map;

final class WwwAuthenticateParser {
    private final boolean basic;
    private final Map<String, String> parameters = new HashMap<>();

    WwwAuthenticateParser(boolean basic, Map<String, String> parameters) {
        this.basic = basic;
        this.parameters.putAll(parameters);
    }

    boolean isBasic() {
        return basic;
    }

    Map<String, String> getParameters() {
        return parameters;
    }
}
