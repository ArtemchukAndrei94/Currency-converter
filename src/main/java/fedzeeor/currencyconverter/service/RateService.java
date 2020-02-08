package fedzeeor.currencyconverter.service;

import fedzeeor.currencyconverter.model.Rate;
import fedzeeor.currencyconverter.repository.CurrencyRepo;
import fedzeeor.currencyconverter.repository.RateRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class RateService {
    private final RateRepo rateRepo;
    private final CurrencyRepo currencyRepo;
    private final RestTemplateService restTemplateService;


    public RateService(RateRepo rateRepo, CurrencyRepo currencyRepo, RestTemplateService restTemplateService) {
        this.rateRepo = rateRepo;
        this.currencyRepo = currencyRepo;
        this.restTemplateService = restTemplateService;
    }

    public Double getResultOfConversion(String charCodeFromConversion, String charCodeToConversion, Integer count) {

        Rate rateFromConversion = rateRepo.getOne(currencyRepo.findByCharcode(charCodeFromConversion).getRate().getId());
        Rate rateToConversion = rateRepo.getOne(currencyRepo.findByCharcode(charCodeToConversion).getRate().getId());

        if (rateFromConversion.getDateCurrentUpdate().isBefore(LocalDate.now()))
            restTemplateService.updateRate();

        return conversion(rateFromConversion, rateToConversion, count);
    }

    private Double conversion(Rate rateFromConversion, Rate rateToConversion, Integer count) {
        return round((rateFromConversion.getValueRateOnRUB() / rateFromConversion.getNominal() * count)
                / (rateToConversion.getValueRateOnRUB() / rateToConversion.getNominal()));
    }

    private static Double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
