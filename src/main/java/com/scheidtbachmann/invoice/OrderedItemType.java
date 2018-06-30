
package com.scheidtbachmann.invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;


/**
 * Products, invoice items and their prices.
 * 
 * <p>Java class for orderedItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderedItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="position" type="{}positionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderedItemType", propOrder = {
    "positions"
})
public final class OrderedItemType {
    @XmlElementWrapper(name = "positions")
    @XmlElement(name = "position")
    @Valid
    private final List<PositionType> positions = new ArrayList<>();

    @XmlAttribute(name = "id", required = true)
    @NotNull
    private final int id;

    @XmlAttribute(name = "name", required = true)
    @NotNull
    private final String name;

    OrderedItemType() {
        id = 0;
        name = null;
    }

    public OrderedItemType(int id, String name, List<PositionType> positions) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.positions.addAll(positions);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != OrderedItemType.class) {
            return false;
        }
        OrderedItemType that = (OrderedItemType) o;
        return Objects.equals(id, that.id)
                && Objects.equals(positions, that.positions)
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return hash(positions, id, name);
    }
}
