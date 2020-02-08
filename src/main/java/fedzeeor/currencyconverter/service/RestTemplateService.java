package fedzeeor.currencyconverter.service;

import fedzeeor.currencyconverter.dto.CurrenciesDto;
import fedzeeor.currencyconverter.model.Currency;
import fedzeeor.currencyconverter.model.Rate;
import fedzeeor.currencyconverter.repository.CurrencyRepo;
import fedzeeor.currencyconverter.repository.RateRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RestTemplateService {
    private Currency currencyRUR = new Currency(null, null, "RUR", "Росиийский Рубль", null);
    private Rate rateRUR = new Rate(1, 1.0, LocalDate.now());

    private final RestTemplate restTemplate;
    private final CurrencyRepo currencyRepo;
    private final RateRepo rateRepo;

    public RestTemplateService(RestTemplate restTemplate, CurrencyRepo currencyRepo, RateRepo rateRepo) {
        this.restTemplate = restTemplate;
        this.currencyRepo = currencyRepo;
        this.rateRepo = rateRepo;
    }

    @PostConstruct
    public void getCurrenciesAndRates() throws Exception {
        rateRepo.deleteAll();

        CurrenciesDto currenciesDto = requestQuotationsCurrencies();

        if (currenciesDto != null) {
            Rate rateRURFromDB = rateRepo.save(rateRUR);
            currencyRUR.setRate(rateRURFromDB);
            currencyRepo.save(currencyRUR);

            currenciesDto.getCurrencyDtoList().forEach(currencyDto -> {
                Rate rate = rateRepo.save(new Rate(
                        currencyDto.getNominal(),
                        currencyDto.getValue(),
                        LocalDate.now()));

                currencyRepo.save(new Currency(
                        currencyDto.getValuteID(),
                        currencyDto.getNumCode(),
                        currencyDto.getCharCode(),
                        currencyDto.getName(),
                        rate));
            });
        } else {
            try {
                throw new Exception("Failed to get up-to-date exchange rate information.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void updateRate() {
        CurrenciesDto currenciesDto = requestQuotationsCurrencies();

        if (currenciesDto != null) {
            currenciesDto.getCurrencyDtoList().forEach(currencyDto -> {
                Rate rate = rateRepo.getOne(currencyRepo.findByCharcode(currencyDto.getCharCode()).getRate().getId());

                rate.setDateCurrentUpdate(LocalDate.now());
                rate.setNominal(currencyDto.getNominal());
                rate.setValueRateOnRUB(currencyDto.getValue());

                rateRepo.save(rate);

            });
        } else {
            try {
                throw new Exception("Failed to get up-to-date exchange rate information.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private CurrenciesDto requestQuotationsCurrencies() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = localDate.format(formatter);

        return restTemplate.getForObject(
                "http://www.cbr.ru/scripts/XML_daily.asp?date_req={date}",
                CurrenciesDto.class,
                dateString);
    }
}
