package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

public interface ExchangeRatePanel {

    void createExchangeRate(CreateExchangeRateCommand cmd);

}
