package pl.com.bottega.exchangerate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String currency;

    @Digits(integer = 5, fraction = 4)
    private BigDecimal rate;

    public ExchangeRate() {
    }

    public ExchangeRate(CreateExchangeRateCommand cmd) {
        this.date = cmd.getDate();
        this.currency = cmd.getCurrency();
        this.rate = cmd.getRate();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

}
