package pl.com.bottega.exchangerate.domain.commands;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculateExchangeCommand implements Validatable {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String from;
    private String to;
    private BigDecimal amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (isEmpty(date))
            errors.add("date", "is required");
        if (isEmpty(from))
            errors.add("from", "is required");
        if (isEmpty(to))
            errors.add("to", "is required");
        if (isEmpty(amount))
            errors.add("amount", "is required");

    }

}
