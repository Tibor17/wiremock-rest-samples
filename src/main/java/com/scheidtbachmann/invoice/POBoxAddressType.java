
package com.scheidtbachmann.invoice;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.util.Objects;


/**
 * PO Box address.
 * 
 * <p>Java class for POBoxAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="POBoxAddressType">
 *   &lt;complexContent>
 *     &lt;extension base="{}addressType">
 *       &lt;sequence>
 *         &lt;element name="postBox" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POBoxAddressType", propOrder = {
    "postBox"
})
public final class POBoxAddressType
    extends AddressType
{
    @XmlElement(required = true)
    @NotNull
    private final String postBox;

    POBoxAddressType() {
        this(null, (short) 0, null, null, null, null, null);
    }

    public POBoxAddressType(String buildingName, short postalCode, String district, String city, String state, String country,
                            String postBox) {
        super(buildingName, postalCode, district, city, state, country);
        this.postBox = postBox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != POBoxAddressType.class) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        }
        POBoxAddressType that = (POBoxAddressType) o;
        return Objects.equals(postBox, that.postBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), postBox);
    }
}
