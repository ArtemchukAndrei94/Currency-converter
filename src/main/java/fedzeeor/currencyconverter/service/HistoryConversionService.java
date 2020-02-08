package fedzeeor.currencyconverter.service;

import fedzeeor.currencyconverter.model.Currency;
import fedzeeor.currencyconverter.model.HistoryConversion;
import fedzeeor.currencyconverter.model.User;
import fedzeeor.currencyconverter.repository.CurrencyRepo;
import fedzeeor.currencyconverter.repository.HistoryConversionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryConversionService {
    private final HistoryConversionRepo historyConversionRepo;
    private final CurrencyRepo currencyRepo;


    public HistoryConversionService(HistoryConversionRepo historyConversionRepo, CurrencyRepo currencyRepo) {
        this.historyConversionRepo = historyConversionRepo;
        this.currencyRepo = currencyRepo;
    }

    public List<HistoryConversion> getAll(User user) {
        return historyConversionRepo.findAllByUsr(user);
    }


    public void save(User user, String charCodeFromConversion, String charCodeToConversion, Integer count, Double resultOfConversion) {
        Currency currencyFromConversion = currencyRepo.findByCharcode(charCodeFromConversion);
        Currency currencyToConversion = currencyRepo.findByCharcode(charCodeToConversion);

        String sourceCurrencyName = charCodeFromConversion + " (" + currencyFromConversion.getName() + ")";
        String targetCurrencyName = charCodeToConversion + " (" + currencyToConversion.getName() + ")";

        HistoryConversion historyConversion = new HistoryConversion(
                null,
                sourceCurrencyName,
                targetCurrencyName,
                count,
                resultOfConversion,
                LocalDate.now(),
                user
        );

        historyConversionRepo.save(historyConversion);

    }
}
