package pl.com.bottega.exchangerate.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.api.ExchangeRateManager;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeQuery;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

@RestController
public class ExchangeController {

    private ExchangeRateManager exchangeRateManager;

    private ExchangeRateCalculator exchangeRateCalculator;

    public ExchangeController(ExchangeRateManager exchangeRateManager, ExchangeRateCalculator exchangeRateCalculator) {
        this.exchangeRateManager = exchangeRateManager;
        this.exchangeRateCalculator = exchangeRateCalculator;
    }

    @PutMapping("/exchange-rate")
    public void createExchangeRate(@RequestBody CreateExchangeRateCommand cmd) {
        exchangeRateManager.createExchangeRate(cmd);
    }

    @GetMapping("/calculation")
    public ExchangeCalculationResult calculate(CalculateExchangeQuery query) {
        return exchangeRateCalculator.calculate(query);
    }

}
