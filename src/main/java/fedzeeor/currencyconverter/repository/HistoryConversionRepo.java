package fedzeeor.currencyconverter.repository;

import fedzeeor.currencyconverter.model.HistoryConversion;
import fedzeeor.currencyconverter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryConversionRepo extends JpaRepository<HistoryConversion, Integer> {

    List<HistoryConversion> findAllByUsr(User user);
}
