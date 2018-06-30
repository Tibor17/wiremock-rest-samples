
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
 * Plain address.
 * 
 * <p>Java class for PlainAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlainAddressType">
 *   &lt;complexContent>
 *     &lt;extension base="{}addressType">
 *       &lt;sequence>
 *         &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="houseNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlainAddressType", propOrder = {
    "street",
    "houseNo"
})
public final class PlainAddressType
    extends AddressType {

    @XmlElement(required = true)
    @NotNull
    private final String street;

    @XmlElement(required = true)
    @NotNull
    private final String houseNo;

    PlainAddressType() {
        super();
        street = null;
        houseNo = null;
    }

    public PlainAddressType(String buildingName, short postalCode, String district, String city, String state, String country,
                            String street, String houseNo) {
        super(buildingName, postalCode, district, city, state, country);
        this.street = requireNonNull(street);
        this.houseNo = requireNonNull(houseNo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != PlainAddressType.class) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        }
        PlainAddressType that = (PlainAddressType) o;
        return Objects.equals(street, that.street)
                && Objects.equals(houseNo, that.houseNo);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), street, houseNo);
    }
}
