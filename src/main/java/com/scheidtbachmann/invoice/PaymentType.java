
package com.scheidtbachmann.invoice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for paymentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentMethods" type="{}paymentMethodType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentType", propOrder = {
    "paymentMethod"
})
public final class PaymentType {

    @XmlElement(name = "paymentMethods", required = true)
    @NotNull
    @Valid
    private final PaymentMethodType paymentMethod;

    PaymentType() {
        paymentMethod = null;
    }

    public PaymentType(PaymentMethodType paymentMethod) {
        this.paymentMethod = requireNonNull(paymentMethod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != PaymentType.class) {
            return false;
        }
        PaymentType that = (PaymentType) o;
        return Objects.equals(paymentMethod, that.paymentMethod);
    }

    @Override
    public int hashCode() {
        return hash(paymentMethod);
    }
}
