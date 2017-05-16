package pl.com.bottega.exchangerate.api.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.api.ExchangeCalculationResult;
import pl.com.bottega.exchangerate.api.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.api.ExchangeCatalog;
import pl.com.bottega.exchangerate.api.ExchangeRateDto;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;
import pl.com.bottega.exchangerate.domain.commands.CalculateExchangeQuery;
import pl.com.bottega.exchangerate.domain.commands.Validatable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

@Transactional
public class StandardExchangeRateCalculator implements ExchangeRateCalculator {

    private ExchangeCatalog exchangeCatalog;

    public StandardExchangeRateCalculator(ExchangeCatalog exchangeCatalog) {
        this.exchangeCatalog = exchangeCatalog;
    }

    @Override
    public ExchangeCalculationResult calculate(CalculateExchangeQuery cmd) {
        String from = cmd.getFrom();
        String to = cmd.getTo();
        LocalDate date = cmd.getDate();
        BigDecimal amount = cmd.getAmount();

        BigDecimal calculatedAmount = BigDecimal.ONE;

        if (from.equals("PLN")) {
            ExchangeRateDto exchangeRateDto = exchangeCatalog.get(date, to);
            BigDecimal rate = exchangeRateDto.getRate();
            calculatedAmount = amount.divide(rate, new MathContext(4));
        }

        if (to.equals("PLN")) {
            ExchangeRateDto exchangeRateDto = exchangeCatalog.get(date, from);
            BigDecimal rate = exchangeRateDto.getRate();
            calculatedAmount = amount.multiply(rate);
        }

        if (!(from.equals("PLN")) && !(to.equals("PLN"))) {
            ExchangeRateDto exchangeRateDtoFrom = exchangeCatalog.get(date, from);
            BigDecimal rateFrom = exchangeRateDtoFrom.getRate();
            ExchangeRateDto exchangeRateDtoTo = exchangeCatalog.get(date, to);
            BigDecimal rateTo = exchangeRateDtoTo.getRate();
            calculatedAmount = (amount.multiply(rateFrom)).divide(rateTo, new MathContext(4));
        }
        return new ExchangeCalculationResult(from, to, amount, calculatedAmount, date);
    }

}
