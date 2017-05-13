package pl.com.bottega.exchangerate.api.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeCommand;

@Transactional
public class StandardExchangeCalulator implements ExchangeCalculator {

    private ExchangeRepository exchangeRepository;

    public StandardExchangeCalulator(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public ExchangeCalculationResult calculate(CalculateExchangeCommand cmd) {
        return null;
    }

}
