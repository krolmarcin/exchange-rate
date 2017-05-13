package pl.com.bottega.exchangerate.domain;

import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeCommand;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

public interface AdminPanel {

    void createExchangeRate(CreateExchangeRateCommand cmd);

}
