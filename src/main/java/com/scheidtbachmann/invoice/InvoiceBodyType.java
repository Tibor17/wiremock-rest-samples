
package com.scheidtbachmann.invoice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.*;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for invoiceBodyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="invoiceBodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderedItems" type="{}orderedItemsType"/>
 *         &lt;element name="totalPriceNett" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="vats" type="{}vatPricesType"/>
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="billingPeriodFrom" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="billingPeriodTo" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="taxIdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invoiceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invoiceIssueDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="paymentDueDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoiceBodyType", propOrder = {
    "orderedItems",
    "totalPriceNett",
    "vats",
    "totalPrice",
    "billingPeriodFrom",
    "billingPeriodTo",
    "taxIdentificationNumber",
    "invoiceNumber",
    "invoiceIssueDate",
    "paymentDueDate",
    "currencyCode"
})
public final class InvoiceBodyType
{
    @XmlElementWrapper(name = "orderedItems")
    @XmlElement(name = "orderedItem", required = true)
    @Valid
    private final List<OrderedItemType> orderedItems = new ArrayList<>();

    @XmlElement(required = true)
    @NotNull
    private final BigDecimal totalPriceNett;

    @XmlElementWrapper(name = "vats")
    @XmlElement(name = "vat", required = true)
    @Valid
    private final Collection<VatPriceType> vats = new ArrayList<>();

    @XmlElement(required = true)
    @NotNull
    private final BigDecimal totalPrice;

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(DateWithoutTimezoneAdapter.class)
    @NotNull
    private final Date billingPeriodFrom;

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(DateWithoutTimezoneAdapter.class)
    @NotNull
    private final Date billingPeriodTo;

    @XmlElement(required = true)
    @NotNull
    private final String taxIdentificationNumber;

    @XmlElement(required = true)
    @NotNull
    private final String invoiceNumber;

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(DateWithoutTimezoneAdapter.class)
    @NotNull
    private final Date invoiceIssueDate;

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(DateWithoutTimezoneAdapter.class)
    @NotNull
    private final Date paymentDueDate;

    @XmlElement(required = true)
    @NotNull
    private final String currencyCode;

    InvoiceBodyType() {
        totalPriceNett = null;
        totalPrice = null;
        billingPeriodFrom = null;
        billingPeriodTo = null;
        taxIdentificationNumber = null;
        invoiceNumber = null;
        invoiceIssueDate = null;
        paymentDueDate = null;
        currencyCode = null;
    }

    public InvoiceBodyType(BigDecimal totalPriceNett, Collection<VatPriceType> vats, BigDecimal totalPrice,
                           Collection<OrderedItemType> orderedItems, Date billingPeriodFrom, Date billingPeriodTo,
                           String taxIdentificationNumber, String invoiceNumber, Date invoiceIssueDate, Date paymentDueDate,
                           Currency currency) {
        this.totalPriceNett = requireNonNull(totalPriceNett);
        this.vats.addAll(vats);
        this.totalPrice = requireNonNull(totalPrice);
        this.orderedItems.addAll(orderedItems);
        this.billingPeriodFrom = requireNonNull(billingPeriodFrom);
        this.billingPeriodTo = requireNonNull(billingPeriodTo);
        this.taxIdentificationNumber = requireNonNull(taxIdentificationNumber);
        this.invoiceNumber = requireNonNull(invoiceNumber);
        this.invoiceIssueDate = requireNonNull(invoiceIssueDate);
        this.paymentDueDate = requireNonNull(paymentDueDate);
        currencyCode = requireNonNull(currency).getCurrencyCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != InvoiceBodyType.class) {
            return false;
        }
        InvoiceBodyType that = (InvoiceBodyType) o;
        return Objects.equals(orderedItems, that.orderedItems)
                && Objects.equals(totalPriceNett, that.totalPriceNett)
                && Objects.equals(vats, that.vats)
                && Objects.equals(totalPrice, that.totalPrice)
                && Objects.equals(billingPeriodFrom, that.billingPeriodFrom)
                && Objects.equals(billingPeriodTo, that.billingPeriodTo)
                && Objects.equals(taxIdentificationNumber, that.taxIdentificationNumber)
                && Objects.equals(invoiceNumber, that.invoiceNumber)
                && Objects.equals(invoiceIssueDate, that.invoiceIssueDate)
                && Objects.equals(paymentDueDate, that.paymentDueDate)
                && Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return hash(orderedItems, totalPriceNett, vats, totalPrice, billingPeriodFrom, billingPeriodTo, taxIdentificationNumber,
                invoiceNumber, invoiceIssueDate, paymentDueDate, currencyCode);
    }
}
