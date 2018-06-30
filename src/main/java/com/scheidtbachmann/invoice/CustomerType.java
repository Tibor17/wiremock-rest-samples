
package com.scheidtbachmann.invoice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for customerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="companyData" type="{}taxIdentificationType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="customerNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerType", propOrder = {
    "companyData"
})
public final class CustomerType
{

    @XmlElement(required = true)
    @NotNull
    @Valid
    private final TaxIdentificationType companyData;

    @XmlAttribute(name = "customerNumber", required = true)
    @NotNull
    private final String customerNumber;

    CustomerType() {
        companyData = null;
        customerNumber = null;
    }

    public CustomerType(TaxIdentificationType companyData, String customerNumber) {
        this.companyData = requireNonNull(companyData);
        this.customerNumber = requireNonNull(customerNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != CustomerType.class) {
            return false;
        }
        CustomerType that = (CustomerType) o;
        return Objects.equals(companyData, that.companyData)
                && Objects.equals(customerNumber, that.customerNumber);
    }

    @Override
    public int hashCode() {
        return hash(companyData, customerNumber);
    }
}
