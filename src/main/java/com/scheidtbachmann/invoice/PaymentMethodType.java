
package com.scheidtbachmann.invoice;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for paymentMethodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentMethodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="method" type="{}methodEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentMethodType", propOrder = {
    "method"
})
public final class PaymentMethodType {

    @XmlElement(required = true)
    @NotNull
    private final MethodEnum method;

    PaymentMethodType() {
        method = null;
    }

    public PaymentMethodType(MethodEnum method) {
        this.method = requireNonNull(method);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != PaymentMethodType.class) {
            return false;
        }
        PaymentMethodType that = (PaymentMethodType) o;
        return Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return hash(method);
    }
}
