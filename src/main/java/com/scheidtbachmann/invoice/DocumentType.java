
package com.scheidtbachmann.invoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for documentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customizationToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentType", propOrder = {
    "customizationToken"
})
public final class DocumentType {

    @XmlElement(required = true, nillable = true)
    private final String customizationToken;

    DocumentType() {
        this(null);
    }

    public DocumentType(String customizationToken) {
        this.customizationToken = customizationToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != DocumentType.class) {
            return false;
        }

        DocumentType that = (DocumentType) o;
        return Objects.equals(customizationToken, that.customizationToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customizationToken);
    }
}
