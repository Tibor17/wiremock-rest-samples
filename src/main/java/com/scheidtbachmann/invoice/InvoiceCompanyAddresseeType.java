
package com.scheidtbachmann.invoice;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.Objects;

import static com.scheidtbachmann.invoice.ObjectFactory.OBJECT_FACTORY;
import static com.scheidtbachmann.invoice.ObjectFactory.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for invoiceCompanyAddresseeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="invoiceCompanyAddresseeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="additionalName1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalName2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoiceCompanyAddresseeType", propOrder = {
    "name",
    "additionalName1",
    "additionalName2"
})
public final class InvoiceCompanyAddresseeType {

    @XmlElement(required = true)
    @NotNull
    private final String name;

    @XmlElementRef(name = "additionalName1", type = JAXBElement.class, required = false)
    private final JAXBElement<String> additionalName1;

    @XmlElementRef(name = "additionalName2", type = JAXBElement.class, required = false)
    private final JAXBElement<String> additionalName2;

    InvoiceCompanyAddresseeType() {
        name = null;
        additionalName1 = null;
        additionalName2 = null;
    }

    public InvoiceCompanyAddresseeType(String name, String additionalName1, String additionalName2) {
        this.name = requireNonNull(name);

        this.additionalName1 =
                additionalName1 != null && !additionalName1.isEmpty()
                        ? OBJECT_FACTORY.createInvoiceCompanyAddresseeTypeAdditionalName1(additionalName1)
                        : null;

        this.additionalName2 =
                additionalName2 != null && !additionalName2.isEmpty()
                        ? OBJECT_FACTORY.createInvoiceCompanyAddresseeTypeAdditionalName2(additionalName2)
                        : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != InvoiceCompanyAddresseeType.class) {
            return false;
        }
        InvoiceCompanyAddresseeType that = (InvoiceCompanyAddresseeType) o;
        return Objects.equals(name, that.name)
                && ObjectFactory.equals(additionalName1, that.additionalName1)
                && ObjectFactory.equals(additionalName2, that.additionalName2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hash(additionalName1), hash(additionalName2));
    }
}
