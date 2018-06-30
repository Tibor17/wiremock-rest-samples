package com.scheidtbachmann.invoice;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateWithoutTimezoneAdapter extends XmlAdapter<String, Date> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date unmarshal(String v) throws Exception {
        return v == null ? null : DATE_FORMAT.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return v == null ? null : DATE_FORMAT.format(v);
    }
}
