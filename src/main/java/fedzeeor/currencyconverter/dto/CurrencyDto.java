package fedzeeor.currencyconverter.dto;

import fedzeeor.currencyconverter.util.DoubleAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Setter
@Getter
public class CurrencyDto {

    @XmlAttribute(name = "ID")
    private String valuteID;
    @XmlElement(name = "NumCode")
    private Integer numCode;
    @XmlElement(name = "CharCode")
    private String charCode;
    @XmlElement(name = "Nominal")
    private Integer nominal;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double value;

}
