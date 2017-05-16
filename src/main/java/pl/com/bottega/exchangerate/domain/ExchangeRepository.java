package pl.com.bottega.exchangerate.domain;

import java.time.LocalDate;

public interface ExchangeRepository {

    void put(ExchangeRate exchangeRate);

    ExchangeRate find(LocalDate date, String currency);

    void update(ExchangeRate exchangeRate);

    boolean exist(LocalDate date, String currency);

}

