package pl.com.bottega.exchangerate.domain.commands;

import java.math.BigDecimal;

public class CreateExchangeRateCommand implements Validatable {

    private String date;
    private String currency;
    private BigDecimal rate;

    public String getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (isEmpty(date))
            errors.add("date", "is required");
        if (isEmpty(currency))
            errors.add("currency", "is required");
        if (currency != null && !(currency.trim().length() == 3))
            errors.add("currency", "has invalid format");
        if (rate == null || isEmpty(rate))
            errors.add("rate", "is required");
        if (rate != null && (rate.compareTo(BigDecimal.ZERO) < 0))
            errors.add("rate", "must be > than 0.0");
    }

    @Override
    public boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

}
