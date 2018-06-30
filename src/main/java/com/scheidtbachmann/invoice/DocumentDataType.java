
package com.scheidtbachmann.invoice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for documentDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="document" type="{}documentType"/>
 *         &lt;element name="customer" type="{}customerType"/>
 *         &lt;element name="contract" type="{}contractType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentDataType", propOrder = {
    "document",
    "customer",
    "contract"
})
// Do NOT add @XmlRootElement(name = "documentData") if ObjectFactory is specified
public final class DocumentDataType {
    @XmlElement(required = true)
    @NotNull
    @Valid
    private final DocumentType document;

    @XmlElement(required = true)
    @NotNull
    @Valid
    private final CustomerType customer;

    @XmlElement(required = true)
    @NotNull
    @Valid
    private final ContractType contract;

    DocumentDataType() {
        document = null;
        customer = null;
        contract = null;
    }

    public DocumentDataType(DocumentType document, CustomerType customer, ContractType contract) {
        this.document = requireNonNull(document);
        this.customer = requireNonNull(customer);
        this.contract = requireNonNull(contract);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != DocumentDataType.class) {
            return false;
        }
        DocumentDataType that = (DocumentDataType) o;
        return Objects.equals(document, that.document)
                && Objects.equals(customer, that.customer)
                && Objects.equals(contract, that.contract);
    }

    @Override
    public int hashCode() {
        return hash(document, customer, contract);
    }
}
