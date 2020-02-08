package fedzeeor.currencyconverter.controller;

import fedzeeor.currencyconverter.model.User;
import fedzeeor.currencyconverter.service.CurrencyService;
import fedzeeor.currencyconverter.service.HistoryConversionService;
import fedzeeor.currencyconverter.service.RateService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    private final CurrencyService currencyService;
    private final RateService rateService;
    private final HistoryConversionService historyConversionService;

    public MainController(CurrencyService currencyService,
                          RateService rateService,
                          HistoryConversionService historyConversionService
    ) {
        this.currencyService = currencyService;
        this.rateService = rateService;
        this.historyConversionService = historyConversionService;
    }

    @GetMapping
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("main")
    public String getCurrencies(Map<String, Object> model) {
        model.put("currencies", currencyService.getCurrencies());
        model.put("result", "");
        model.put("startValue", "");
        return "main";
    }

    @PostMapping("main")
    public String conversionAndWriteHistory(
            @AuthenticationPrincipal User user,
            @RequestParam("charcode1") String charCodeFromConversion,
            @RequestParam("charcode2") String charCodeToConversion,
            @RequestParam("count") Integer count,
            Map<String, Object> model
    ) {
        Double resultOfConversion = rateService.getResultOfConversion(
                charCodeFromConversion,
                charCodeToConversion,
                count
        );

        model.put("currencies", currencyService.getCurrencies());
        model.put("result", resultOfConversion.toString());
        model.put("startValue", count.toString());

        historyConversionService.save(user, charCodeFromConversion, charCodeToConversion, count, resultOfConversion);

        return "main";
    }


}