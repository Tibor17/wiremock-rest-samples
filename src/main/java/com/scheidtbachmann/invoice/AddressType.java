
package com.scheidtbachmann.invoice;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.Objects;

import static com.scheidtbachmann.invoice.ObjectFactory.OBJECT_FACTORY;
import static com.scheidtbachmann.invoice.ObjectFactory.hash;
import static java.util.Objects.requireNonNull;


/**
 * <p>Java class for addressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buildingName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addressType", propOrder = {
    "buildingName",
    "postalCode",
    "district",
    "city",
    "state",
    "country"
})
@XmlSeeAlso({
    POBoxAddressType.class,
    PlainAddressType.class
})
public abstract class AddressType {

    @XmlElementRef(name = "buildingName", type = JAXBElement.class, required = false)
    private final JAXBElement<String> buildingName;

    @NotNull
    @DecimalMax("32767")
    @DecimalMin("-32768")
    private final short postalCode;

    @XmlElementRef(name = "district", type = JAXBElement.class, required = false)
    private final JAXBElement<String> district;

    @XmlElement(required = true)
    @NotNull
    private final String city;

    @XmlElementRef(name = "state", type = JAXBElement.class, required = false)
    private final JAXBElement<String> state;

    @XmlElement(required = true)
    @NotNull
    private final String country;

    AddressType() {
        buildingName = null;
        postalCode = 0;
        district = null;
        state = null;
        city = null;
        country = null;
    }

    public AddressType(String buildingName, short postalCode, String district, String city, String state, String country) {
        this.buildingName =
                buildingName != null && !buildingName.isEmpty() ? OBJECT_FACTORY.createAddressTypeBuildingName(buildingName) : null;
        this.postalCode = postalCode;
        this.district = district != null && !district.isEmpty() ? OBJECT_FACTORY.createAddressTypeDistrict(district) : null;
        this.city = requireNonNull(city);
        this.state = state != null && !state.isEmpty() ? OBJECT_FACTORY.createAddressTypeState(state) : null;
        this.country = requireNonNull(country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AddressType)) {
            return false;
        }
        AddressType that = (AddressType) o;
        return ObjectFactory.equals(buildingName, that.buildingName)
                && Objects.equals(postalCode, that.postalCode)
                && ObjectFactory.equals(district, that.district)
                && Objects.equals(city, that.city)
                && ObjectFactory.equals(state, that.state)
                && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash(buildingName), postalCode, hash(district), city, hash(state), country);
    }
}
