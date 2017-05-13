package pl.com.bottega.exchangerate.api.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.api.ExchangeCatalog;
import pl.com.bottega.exchangerate.api.ExchangeRateDto;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeCommand;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

@Transactional
public class StandardExchangeCalculator implements ExchangeCalculator {

    private ExchangeRepository exchangeRepository;
    private ExchangeCatalog exchangeCatalog;

    public StandardExchangeCalculator(ExchangeRepository exchangeRepository, ExchangeCatalog exchangeCatalog) {
        this.exchangeRepository = exchangeRepository;
        this.exchangeCatalog = exchangeCatalog;
    }

    @Override
    public ExchangeCalculationResult calculate(CalculateExchangeCommand cmd) {
        String from = cmd.getFrom();
        String to = cmd.getTo();
        LocalDate date = cmd.getDate();
        BigDecimal amount = cmd.getAmount();

        BigDecimal calculatetAmount = BigDecimal.ONE;

        if (from.equals("PLN")) {
            ExchangeRateDto exchangeRateDto = exchangeCatalog.get(date, to);
            BigDecimal rate = exchangeRateDto.getRate();
            calculatetAmount = amount.divide(rate, new MathContext(4));
        }

        if (to.equals("PLN")) {
            ExchangeRateDto exchangeRateDto = exchangeCatalog.get(date, from);
            BigDecimal rate = exchangeRateDto.getRate();
            calculatetAmount = amount.multiply(rate);
        }


        return new ExchangeCalculationResult(from, to, amount, calculatetAmount, date);
    }

}
