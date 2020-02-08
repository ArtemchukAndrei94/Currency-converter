package fedzeeor.currencyconverter.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DoubleAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String v) throws Exception {
        return Double.parseDouble(v.replace(",", "."));
    }

    @Override
    public String marshal(Double v) throws Exception {
        return String.valueOf(v);
    }
}