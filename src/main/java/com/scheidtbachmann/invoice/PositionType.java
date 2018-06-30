
package com.scheidtbachmann.invoice;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for positionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="positionType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="singleOrderNettPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="ordersCount" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="totalNettPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="vat" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="totalTaxPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="totalGrossPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="currencyCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "positionType", propOrder = {
    "value"
})
public final class PositionType {
    @XmlAttribute(name = "singleOrderNettPrice", required = true)
    @NotNull
    private final BigDecimal singleOrderNettPrice;

    @XmlAttribute(name = "ordersCount", required = true)
    @NotNull
    private final int ordersCount;

    @XmlAttribute(name = "totalNettPrice", required = true)
    @NotNull
    private final BigDecimal totalNettPrice;

    @XmlAttribute(name = "vat", required = true)
    @NotNull
    private final BigDecimal vat;

    @XmlAttribute(name = "totalTaxPrice", required = true)
    @NotNull
    private final BigDecimal totalTaxPrice;

    @XmlAttribute(name = "totalGrossPrice", required = true)
    @NotNull
    private final BigDecimal totalGrossPrice;

    @XmlAttribute(name = "currencyCode", required = true)
    @NotNull
    private final String currencyCode;

    @XmlValue
    @NotNull
    private final String value;

    PositionType() {
        singleOrderNettPrice = null;
        ordersCount = 0;
        totalNettPrice = null;
        vat = null;
        totalTaxPrice = null;
        totalGrossPrice = null;
        currencyCode = null;
        value = null;
    }

    public PositionType(BigDecimal singleOrderNettPrice, int ordersCount, BigDecimal totalNettPrice, BigDecimal vat,
                        BigDecimal totalTaxPrice, BigDecimal totalGrossPrice, Currency currency, String value) {
        this.singleOrderNettPrice = requireNonNull(singleOrderNettPrice);
        this.ordersCount = requireNonNull(ordersCount);
        this.totalNettPrice = requireNonNull(totalNettPrice);
        this.vat = requireNonNull(vat);
        this.totalTaxPrice = requireNonNull(totalTaxPrice);
        this.totalGrossPrice = requireNonNull(totalGrossPrice);
        currencyCode = requireNonNull(currency).getCurrencyCode();
        this.value = requireNonNull(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != PositionType.class) {
            return false;
        }
        PositionType that = (PositionType) o;
        return Objects.equals(singleOrderNettPrice, that.singleOrderNettPrice)
                && Objects.equals(ordersCount, that.ordersCount)
                && Objects.equals(totalNettPrice, that.totalNettPrice)
                && Objects.equals(vat, that.vat)
                && Objects.equals(totalTaxPrice, that.totalTaxPrice)
                && Objects.equals(totalGrossPrice, that.totalGrossPrice)
                && Objects.equals(currencyCode, that.currencyCode)
                && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return hash(singleOrderNettPrice, ordersCount, totalNettPrice, vat, totalTaxPrice, totalGrossPrice, currencyCode, value);
    }
}
