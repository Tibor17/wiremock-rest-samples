
package com.scheidtbachmann.invoice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

import static java.util.Objects.hash;


/**
 * <p>Java class for invoiceCompanyAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="invoiceCompanyAddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{}addressType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoiceCompanyAddressType", propOrder = {
    "address"
})
public final class InvoiceCompanyAddressType {

    @XmlElement(required = true)
    @NotNull
    @Valid
    private final AddressType address;

    InvoiceCompanyAddressType() {
        address = null;
    }

    public InvoiceCompanyAddressType(String buildingName, short postalCode, String district, String city, String state, String country,
                                     String street, String houseNo) {
        address = new PlainAddressType(buildingName, postalCode, district, city, state, country, street, houseNo);
    }

    public InvoiceCompanyAddressType(String buildingName, short postalCode, String district, String city, String state, String country,
                                     String postBox) {
        address = new POBoxAddressType(buildingName, postalCode, district, city, state, country, postBox);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != InvoiceCompanyAddressType.class) {
            return false;
        }
        InvoiceCompanyAddressType that = (InvoiceCompanyAddressType) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return hash(address);
    }
}
