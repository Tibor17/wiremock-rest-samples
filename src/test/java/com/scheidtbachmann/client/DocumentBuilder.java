package com.scheidtbachmann.client;

import com.scheidtbachmann.invoice.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import static com.scheidtbachmann.invoice.MethodEnum.SEPA;
import static java.util.Collections.singleton;

public class DocumentBuilder {

    public DocumentDataType build() {
        TaxIdentificationType taxIdentification = new TaxIdentificationType("19%");

        CustomerType customer = new CustomerType(taxIdentification, "14450002201");

        PaymentMethodType paymentMethod = new PaymentMethodType(SEPA);

        PaymentType payment = new PaymentType(paymentMethod);

        InvoiceCompanyAddresseeType invoiceCompanyAddressee = new InvoiceCompanyAddresseeType("Dr. Max Mustermann", "", "");

        InvoiceCompanyAddressType contact =
                new InvoiceCompanyAddressType("", (short) 123, "", "Berlin", "", "Germany", "Musterstr.", "1");

        InvoiceAddresseeType invoiceAddressee = new InvoiceAddresseeType(invoiceCompanyAddressee, contact);

        List<PositionType> invoiceItems = new ArrayList<>();

        invoiceItems.add(new PositionType(new BigDecimal(3),
                2,
                new BigDecimal(6),
                new BigDecimal(19) /*19%*/,
                new BigDecimal("1.2"),
                new BigDecimal("7.2"),
                Currency.getInstance("EUR"),
                "some product"));

        InvoiceBodyType body =
                new InvoiceBodyType(new BigDecimal(100),
                        singleton(new VatPriceType("19%", new BigDecimal(19), Currency.getInstance("EUR"))),
                        new BigDecimal(119),
                        singleton(new OrderedItemType(1, "Product 1", invoiceItems)),
                        /*01.11.2016*/ new Date(2016 - 1900, 0, 11),
                        /*30.11.2006*/ new Date(2006 - 1900, 11 - 1, 30),
                        "DE 13456789",
                        "0901123456",
                        /*02.11.2016*/ new Date(2016 - 1900, 11 - 1, 2),
                        /*21.11.2016*/ new Date(2016 - 1, 11 - 1, 21),
                        Currency.getInstance("EUR"));

        ContractType contract = new ContractType(payment, invoiceAddressee, "CCN", body);
        DocumentType document = new DocumentType("some operator hash");

        return new DocumentDataType(document, customer, contract);
    }
}
