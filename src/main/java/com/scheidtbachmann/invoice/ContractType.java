
package com.scheidtbachmann.invoice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for contractType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contractType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payment" type="{}paymentType"/>
 *         &lt;element name="invoiceAddressee" type="{}invoiceAddresseeType"/>
 *         &lt;element name="invoiceBody" type="{}invoiceBodyType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="contractNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractType", propOrder = {
    "payment",
    "invoiceAddressee",
    "invoiceBody"
})
public final class ContractType {

    @XmlElement(required = true)
    @NotNull
    @Valid
    private final PaymentType payment;

    @XmlElement(required = true)
    @NotNull
    @Valid
    private final InvoiceAddresseeType invoiceAddressee;

    @XmlAttribute(name = "contractNumber", required = true)
    @NotNull
    private final String contractNumber;

    @XmlElement(required = true)
    @NotNull
    @Valid
    private final InvoiceBodyType invoiceBody;

    ContractType() {
        payment = null;
        invoiceAddressee = null;
        contractNumber = null;
        invoiceBody = null;
    }

    public ContractType(PaymentType payment, InvoiceAddresseeType invoiceAddressee, String contractNumber,
                        InvoiceBodyType invoiceBody) {
        this.payment = requireNonNull(payment);
        this.invoiceAddressee = requireNonNull(invoiceAddressee);
        this.contractNumber = requireNonNull(contractNumber);
        this.invoiceBody = requireNonNull(invoiceBody);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != ContractType.class) {
            return false;
        }
        ContractType that = (ContractType) o;
        return Objects.equals(payment, that.payment)
                && Objects.equals(invoiceAddressee, that.invoiceAddressee)
                && Objects.equals(invoiceBody, that.invoiceBody)
                && Objects.equals(contractNumber, that.contractNumber);
    }

    @Override
    public int hashCode() {
        return hash(payment, invoiceAddressee, invoiceBody, contractNumber);
    }
}
