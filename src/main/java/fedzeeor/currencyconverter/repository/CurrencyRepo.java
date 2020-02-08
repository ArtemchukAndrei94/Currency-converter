package fedzeeor.currencyconverter.repository;

import fedzeeor.currencyconverter.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<Currency, Integer> {

    Currency findByCharcode(String chaCode);

}
