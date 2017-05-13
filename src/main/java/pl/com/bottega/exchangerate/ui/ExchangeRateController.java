package pl.com.bottega.exchangerate.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.domain.AdminPanel;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeCommand;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class ExchangeRateController {

    private AdminPanel adminPanel;

    private ExchangeCalculator exchangeCalculator;

    public ExchangeRateController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping("/exchange-rate")
    public void createExchangeRate(@RequestBody CreateExchangeRateCommand cmd) {
        adminPanel.createExchangeRate(cmd);
    }

    @GetMapping("/calculate")
    public ExchangeCalculationResult calculate(CalculateExchangeCommand cmd) {
        return exchangeCalculator.calculate(cmd);
    }

}
