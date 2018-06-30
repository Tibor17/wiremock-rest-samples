package com.scheidtbachmann.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicAuthenticationParserTest {

    @Test
    public void shouldParseAuthentication() {
        String authentication = "Basic realm=\"User Visible Realm\" charset=\"UTF-8\"";
        WwwAuthenticateParser parser = JRSAdapterClientService.parseAuthenticationHeader(authentication);

        assertThat(parser.isBasic())
                .isTrue();

        assertThat(parser.getParameters())
                .hasSize(2);

        assertThat(parser.getParameters())
                .containsKeys("realm", "charset")
                .containsValues("UTF-8", "User Visible Realm");

        authentication = "  Basic     realm = \"User Visible Realm\" charset = \"UTF-8\" ";
        parser = JRSAdapterClientService.parseAuthenticationHeader(authentication);

        assertThat(parser.isBasic())
                .isTrue();

        assertThat(parser.getParameters())
                .containsKeys("realm", "charset")
                .containsValues("UTF-8", "User Visible Realm")
                .hasSize(2);
    }
}
