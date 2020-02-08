package fedzeeor.currencyconverter.repository;

import fedzeeor.currencyconverter.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepo extends JpaRepository<Rate, Integer> {

}
