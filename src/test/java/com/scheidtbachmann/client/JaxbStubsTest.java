package com.scheidtbachmann.client;

import com.scheidtbachmann.invoice.DocumentDataType;
import org.junit.Test;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class JaxbStubsTest {
    @Test
    public void shouldFormatDateByCountry() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        String formattedDate = df.format(new Date());
        System.out.println(formattedDate);
    }

    @Test
    public void shouldUnmarshall() {
        DocumentDataType actual = new DocumentBuilder().build();

        StringWriter writer = new StringWriter();
        JAXB.marshal(actual, writer);
        DocumentDataType expected = JAXB.unmarshal(new StringReader(writer.toString()), DocumentDataType.class);

        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    public void shouldGenerateXsdSchema() throws Exception {
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(DocumentDataType.class);
        SchemaOutputResolver sor = new MySchemaOutputResolver(writer);

        jaxbContext.generateSchema(sor);

        assertThat(writer.toString())
                .isNotNull();
    }

    private static class MySchemaOutputResolver extends SchemaOutputResolver {
        private final Writer writer;

        public MySchemaOutputResolver(Writer writer) {
            this.writer = writer;
        }

        @Override
        public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
            StreamResult result = new StreamResult(writer);
            result.setSystemId("");
            return result;
        }
    }
}
