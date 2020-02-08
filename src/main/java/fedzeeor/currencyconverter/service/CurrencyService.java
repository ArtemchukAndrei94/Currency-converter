package fedzeeor.currencyconverter.service;

import fedzeeor.currencyconverter.model.Currency;
import fedzeeor.currencyconverter.repository.CurrencyRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepo currencyRepo;

    public CurrencyService(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    public List<Currency> getCurrencies() {
        return currencyRepo.findAll();
    }
}
