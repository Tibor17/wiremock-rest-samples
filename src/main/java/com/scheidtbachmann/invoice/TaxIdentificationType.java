
package com.scheidtbachmann.invoice;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.util.Objects;

import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for taxIdentificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taxIdentificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taxNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taxIdentificationType", propOrder = {
    "taxNumber"
})
public final class TaxIdentificationType {

    @XmlElement(required = true)
    @NotNull
    private final String taxNumber;

    TaxIdentificationType() {
        taxNumber = null;
    }

    public TaxIdentificationType(String taxNumber) {
        this.taxNumber = requireNonNull(taxNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != TaxIdentificationType.class) {
            return false;
        }
        TaxIdentificationType that = (TaxIdentificationType) o;
        return Objects.equals(taxNumber, that.taxNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxNumber);
    }
}
