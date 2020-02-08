package fedzeeor.currencyconverter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer nominal;
    private Double valueRateOnRUB;
    private LocalDate dateCurrentUpdate;

    @OneToOne(mappedBy = "rate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Currency currency;


    public Rate(Integer nominal, Double valueRateOnRUB, LocalDate dateCurrentUpdate) {
        this.nominal = nominal;
        this.valueRateOnRUB = valueRateOnRUB;
        this.dateCurrentUpdate = dateCurrentUpdate;
    }
}
