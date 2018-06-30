package com.scheidtbachmann.client;

import org.jboss.resteasy.util.CaseInsensitiveMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.ClientRequestContext;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.singletonList;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.xml.bind.DatatypeConverter.printBase64Binary;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasicAuthenticationTest {
    @Mock
    private ClientRequestContext requestContext;

    @Before
    public void doMocks() {
        when(requestContext.getHeaders())
                .thenReturn(new CaseInsensitiveMap<>());
    }

    @Test
    public void shouldAddAuthenticationHeader() throws Exception {
        BasicAuthentication filter = new BasicAuthentication("username", "pswd");
        filter.filter(requestContext);

        assertThat(requestContext.getHeaders())
                .isNotEmpty();

        assertThat(requestContext.getHeaders())
                .hasSize(1);

        assertThat(requestContext.getHeaders())
                .containsKey(AUTHORIZATION);

        String authBase64 = printBase64Binary("username:pswd".getBytes(UTF_8));
        assertThat(requestContext.getHeaders())
                .containsEntry(AUTHORIZATION, singletonList("Basic " + authBase64));
    }
}
