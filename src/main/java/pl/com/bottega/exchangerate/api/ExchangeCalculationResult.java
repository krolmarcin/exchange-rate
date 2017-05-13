package pl.com.bottega.exchangerate.api;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangeCalculationResult {

    private String from;
    private String to;
    private BigDecimal amount;
    private BigDecimal calculatedAmount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public ExchangeCalculationResult() {
    }

    public ExchangeCalculationResult(String from, String to, BigDecimal amount, BigDecimal calculatedAmount, LocalDate date) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.calculatedAmount = calculatedAmount;
        this.date = date;
    }

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

    public BigDecimal getCalculatedAmount() {
        return calculatedAmount;
    }

    public void setCalculatedAmount(BigDecimal calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
