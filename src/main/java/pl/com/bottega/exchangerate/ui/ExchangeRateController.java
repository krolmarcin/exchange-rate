package pl.com.bottega.exchangerate.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.api.AdminPanel;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeCommand;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

@RestController
public class ExchangeRateController {

    private AdminPanel adminPanel;

    private ExchangeCalculator exchangeCalculator;

    public ExchangeRateController(AdminPanel adminPanel, ExchangeCalculator exchangeCalculator) {
        this.adminPanel = adminPanel;
        this.exchangeCalculator = exchangeCalculator;
    }

    @PutMapping("/exchange-rate")
    public void createExchangeRate(@RequestBody CreateExchangeRateCommand cmd) {
        adminPanel.createExchangeRate(cmd);
    }

    @GetMapping("/calculation")
    public ExchangeCalculationResult calculate(CalculateExchangeCommand cmd) {
        return exchangeCalculator.calculate(cmd);
    }

}
