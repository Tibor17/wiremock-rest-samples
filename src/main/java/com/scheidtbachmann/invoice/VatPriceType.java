
package com.scheidtbachmann.invoice;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for vatPriceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vatPriceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
@XmlType(name = "vatPriceType", propOrder = {
    "description",
    "price",
    "currencyCode"
})
public final class VatPriceType {

    @XmlElement(required = true)
    @NotNull
    private final String description;

    @XmlElement(required = true)
    @NotNull
    private final BigDecimal price;

    @XmlElement(required = true)
    @NotNull
    private final String currencyCode;

    VatPriceType() {
        description = null;
        price = null;
        currencyCode = null;
    }

    public VatPriceType(String description, BigDecimal price, Currency currency) {
        this.description = requireNonNull(description);
        this.price = requireNonNull(price);
        currencyCode = requireNonNull(currency).getCurrencyCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != VatPriceType.class) {
            return false;
        }
        VatPriceType that = (VatPriceType) o;
        return Objects.equals(description, that.description)
                && Objects.equals(price, that.price)
                && Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return hash(description, price, currencyCode);
    }
}
