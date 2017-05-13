package pl.com.bottega.exchangerate.api.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.api.ExchangeCatalog;
import pl.com.bottega.exchangerate.api.ExchangeRateDto;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;
import pl.com.bottega.exchangerate.domain.NoRateException;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeCommand;
import pl.com.bottega.exchangerate.domain.commands.Validatable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

@Transactional
public class StandardExchangeCalculator implements ExchangeCalculator, Validatable {

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

        if (!(from.equals("PLN") && !(to.equals("PLN")))) {
            ExchangeRateDto exchangeRateDtoFrom = exchangeCatalog.get(date, from);
            BigDecimal rateFrom = exchangeRateDtoFrom.getRate();
            ExchangeRateDto exchangeRateDtoTo = exchangeCatalog.get(date, to);
            BigDecimal rateTo = exchangeRateDtoTo.getRate();
            calculatetAmount = (amount.multiply(rateFrom)).divide(rateTo, new MathContext(4));
        }
        return new ExchangeCalculationResult(from, to, amount, calculatetAmount, date);
    }

    @Override
    public void validate(ValidationErrors errors) {

    }
}
