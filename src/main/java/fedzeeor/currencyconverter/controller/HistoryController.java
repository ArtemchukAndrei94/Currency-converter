package fedzeeor.currencyconverter.controller;

import fedzeeor.currencyconverter.model.User;
import fedzeeor.currencyconverter.service.HistoryConversionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/history")
public class HistoryController {
    private final HistoryConversionService historyConversionService;

    public HistoryController(HistoryConversionService historyConversionService) {
        this.historyConversionService = historyConversionService;
    }

    @GetMapping
    public String getHistoryConversion(
            @AuthenticationPrincipal User user, Map<String, Object>model) {

        model.put("history", historyConversionService.getAll(user));
        return "history";
    }
}
