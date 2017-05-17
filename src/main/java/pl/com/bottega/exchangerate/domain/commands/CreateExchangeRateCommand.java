package pl.com.bottega.exchangerate.domain.commands;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateExchangeRateCommand implements Validatable {

    private LocalDate date;
    private String currency;
    private BigDecimal rate;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public void validate(ValidationErrors errors) {
        validateEmptyDate(errors);
        validateEmptyCurrency(errors);
        validateCurrencyFormat(errors);
        validateEmptyRate(errors);
        validateRateSmallerThenZero(errors);
    }

    private void validateRateSmallerThenZero(ValidationErrors errors) {
        if (rate != null && (rate.compareTo(BigDecimal.ZERO) < 0))
            errors.add("rate", "must be > than 0.0");
    }

    private void validateEmptyRate(ValidationErrors errors) {
        if (rate == null || isEmpty(rate))
            errors.add("rate", "is required");
    }

    private void validateCurrencyFormat(ValidationErrors errors) {
        if (currency != null && !(currency.trim().length() == 3))
            errors.add("currency", "has invalid format");
    }

    private void validateEmptyCurrency(ValidationErrors errors) {
        if (isEmpty(currency))
            errors.add("currency", "is required");
    }

    private void validateEmptyDate(ValidationErrors errors) {
        if (isEmpty(date))
            errors.add("date", "is required");
    }

}
