package fedzeeor.currencyconverter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String valute_id;
    private Integer numcode;
    private String charcode;
    private String name;

    @OneToOne
    @JoinColumn(name = "rate_id")
    private Rate rate;


    public Currency(String valute_id,
                    Integer numcode,
                    String charcode,
                    String name,
                    Rate rate) {
        this.valute_id = valute_id;
        this.numcode = numcode;
        this.charcode = charcode;
        this.name = name;
        this.rate = rate;
    }
}
