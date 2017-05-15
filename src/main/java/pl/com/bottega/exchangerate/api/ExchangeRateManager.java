package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

public interface ExchangeRateManager {

    void createExchangeRate(CreateExchangeRateCommand cmd);

}
