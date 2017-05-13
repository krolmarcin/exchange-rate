package pl.com.bottega.exchangerate.api;

import java.time.LocalDate;

public interface ExchangeCatalog {

    ExchangeRateDto get(LocalDate date, String currency);

}
