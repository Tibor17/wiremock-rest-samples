
package com.scheidtbachmann.invoice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for invoiceAddresseeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="invoiceAddresseeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="companyData" type="{}invoiceCompanyAddresseeType"/>
 *         &lt;element name="contact" type="{}invoiceCompanyAddressType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoiceAddresseeType", propOrder = {
    "invoiceCompanyAddressee",
    "contact"
})
public final class InvoiceAddresseeType {

    @XmlElement(name = "companyData", required = true)
    @NotNull
    @Valid
    private final InvoiceCompanyAddresseeType invoiceCompanyAddressee;

    @XmlElement(required = true)
    @NotNull
    @Valid
    private final InvoiceCompanyAddressType contact;

    InvoiceAddresseeType() {
        invoiceCompanyAddressee = null;
        contact = null;
    }

    public InvoiceAddresseeType(InvoiceCompanyAddresseeType invoiceCompanyAddressee, InvoiceCompanyAddressType contact) {
        this.invoiceCompanyAddressee = requireNonNull(invoiceCompanyAddressee);
        this.contact = requireNonNull(contact);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != InvoiceAddresseeType.class) {
            return false;
        }
        InvoiceAddresseeType that = (InvoiceAddresseeType) o;
        return Objects.equals(invoiceCompanyAddressee, that.invoiceCompanyAddressee)
                && Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return hash(invoiceCompanyAddressee, contact);
    }
}
