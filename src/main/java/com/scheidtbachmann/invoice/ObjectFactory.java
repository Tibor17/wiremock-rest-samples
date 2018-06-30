
package com.scheidtbachmann.invoice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import java.util.Objects;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.scheidtbachmann.invoice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {
    static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    private final static QName _DocumentData_QNAME = new QName("", "documentData");
    private final static QName _InvoiceCompanyAddresseeTypeAdditionalName1_QNAME = new QName("", "additionalName1");
    private final static QName _InvoiceCompanyAddresseeTypeAdditionalName2_QNAME = new QName("", "additionalName2");
    private final static QName _AddressTypeBuildingName_QNAME = new QName("", "buildingName");
    private final static QName _AddressTypeDistrict_QNAME = new QName("", "district");
    private final static QName _AddressTypeState_QNAME = new QName("", "state");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * com.scheidtbachmann.invoice
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PlainAddressType }
     *
     */
    public PlainAddressType createPlainAddressType() {
        return new PlainAddressType();
    }

    /**
     * Create an instance of {@link OrderedItemType }
     *
     */
    public OrderedItemType createOrderedItemType() {
        return new OrderedItemType();
    }

    /**
     * Create an instance of {@link DocumentType }
     *
     */
    public DocumentType createDocumentType() {
        return new DocumentType();
    }

    /**
     * Create an instance of {@link InvoiceCompanyAddressType }
     *
     */
    public InvoiceCompanyAddressType createInvoiceCompanyAddressType() {
        return new InvoiceCompanyAddressType();
    }

    /**
     * Create an instance of {@link CustomerType }
     *
     */
    public CustomerType createCustomerType() {
        return new CustomerType();
    }

    /**
     * Create an instance of {@link TaxIdentificationType }
     *
     */
    public TaxIdentificationType createTaxIdentificationType() {
        return new TaxIdentificationType();
    }

    /**
     * Create an instance of {@link PositionType }
     *
     */
    public PositionType createPositionType() {
        return new PositionType();
    }

    /**
     * Create an instance of {@link InvoiceCompanyAddresseeType }
     *
     */
    public InvoiceCompanyAddresseeType createInvoiceCompanyAddresseeType() {
        return new InvoiceCompanyAddresseeType();
    }

    /**
     * Create an instance of {@link VatPriceType }
     *
     */
    public VatPriceType createVatPriceType() {
        return new VatPriceType();
    }

    /**
     * Create an instance of {@link InvoiceAddresseeType }
     *
     */
    public InvoiceAddresseeType createInvoiceAddresseeType() {
        return new InvoiceAddresseeType();
    }

    /**
     * Create an instance of {@link PaymentType }
     *
     */
    public PaymentType createPaymentType() {
        return new PaymentType();
    }

    /**
     * Create an instance of {@link PaymentMethodType }
     *
     */
    public PaymentMethodType createPaymentMethodType() {
        return new PaymentMethodType();
    }

    /**
     * Create an instance of {@link ContractType }
     *
     */
    public ContractType createContractType() {
        return new ContractType();
    }

    /**
     * Create an instance of {@link DocumentDataType }
     *
     */
    public DocumentDataType createDocumentDataType() {
        return new DocumentDataType();
    }

    /**
     * Create an instance of {@link POBoxAddressType }
     *
     */
    public POBoxAddressType createPOBoxAddressType() {
        return new POBoxAddressType();
    }

    /**
     * Create an instance of {@link InvoiceBodyType }
     *
     */
    public InvoiceBodyType createInvoiceBodyType() {
        return new InvoiceBodyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentDataType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "documentData")
    public JAXBElement<DocumentDataType> createDocumentData(DocumentDataType value) {
        return new JAXBElement<>(_DocumentData_QNAME, DocumentDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "additionalName1", scope = InvoiceCompanyAddresseeType.class)
    public JAXBElement<String> createInvoiceCompanyAddresseeTypeAdditionalName1(String value) {
        return new JAXBElement<>(_InvoiceCompanyAddresseeTypeAdditionalName1_QNAME, String.class,
                InvoiceCompanyAddresseeType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "additionalName2", scope = InvoiceCompanyAddresseeType.class)
    public JAXBElement<String> createInvoiceCompanyAddresseeTypeAdditionalName2(String value) {
        return new JAXBElement<>(_InvoiceCompanyAddresseeTypeAdditionalName2_QNAME, String.class,
                InvoiceCompanyAddresseeType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "buildingName", scope = AddressType.class)
    public JAXBElement<String> createAddressTypeBuildingName(String value) {
        return new JAXBElement<>(_AddressTypeBuildingName_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "district", scope = AddressType.class)
    public JAXBElement<String> createAddressTypeDistrict(String value) {
        return new JAXBElement<>(_AddressTypeDistrict_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "state", scope = AddressType.class)
    public JAXBElement<String> createAddressTypeState(String value) {
        return new JAXBElement<>(_AddressTypeState_QNAME, String.class, AddressType.class, value);
    }

    static <T> boolean equals(JAXBElement<T> a, JAXBElement<T> b) {
        return a == null && b == null
                || a != null && b != null && Objects.equals(a.getValue(), b.getValue());
    }

    static <T> int hash(JAXBElement<T> e) {
        return e == null || e.getValue() == null ? 0 : e.getValue().hashCode();
    }

}
