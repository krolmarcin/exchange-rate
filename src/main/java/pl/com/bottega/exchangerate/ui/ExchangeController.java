package pl.com.bottega.exchangerate.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.api.ExchangeRatePanel;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeQuery;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

@RestController
public class ExchangeController {

    private ExchangeRatePanel exchangeRatePanel;

    private ExchangeRateCalculator exchangeRateCalculator;

    public ExchangeController(ExchangeRatePanel exchangeRatePanel, ExchangeRateCalculator exchangeRateCalculator) {
        this.exchangeRatePanel = exchangeRatePanel;
        this.exchangeRateCalculator = exchangeRateCalculator;
    }

    @PutMapping("/exchange-rate")
    public void createExchangeRate(@RequestBody CreateExchangeRateCommand cmd) {
        exchangeRatePanel.createExchangeRate(cmd);
    }

    @GetMapping("/calculation")
    public ExchangeCalculationResult calculate(CalculateExchangeQuery query) {
        return exchangeRateCalculator.calculate(query);
    }

}
