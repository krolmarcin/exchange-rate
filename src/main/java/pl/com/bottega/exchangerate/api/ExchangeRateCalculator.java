package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeQuery;

public interface ExchangeRateCalculator {

    ExchangeCalculationResult calculate(CalculateExchangeQuery cmd);

}
