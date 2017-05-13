package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeCommand;

public interface ExchangeCalculator {

    ExchangeCalculationResult calculate(CalculateExchangeCommand cmd);

}
