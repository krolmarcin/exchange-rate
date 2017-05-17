package pl.com.bottega.exchangerate.api.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.api.ExchangeRateManager;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

@Transactional
public class StandardExchangeRateManager implements ExchangeRateManager {

    private ExchangeRepository exchangeRepository;

    public StandardExchangeRateManager(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public void createExchangeRate(CreateExchangeRateCommand cmd) {
        ExchangeRate exchangeRateFind = findExchangeRate(cmd);
        updateIfExistCreateIfAbsent(cmd, exchangeRateFind);
    }

    private void updateIfExistCreateIfAbsent(CreateExchangeRateCommand cmd, ExchangeRate exchangeRateFind) {
        if (exchangeRateFind != null) {
            exchangeRateFind.setRate(cmd.getRate());
            exchangeRepository.update(exchangeRateFind);
        } else {
            ExchangeRate exchangeRate = new ExchangeRate(cmd);
            exchangeRepository.put(exchangeRate);
        }
    }

    private ExchangeRate findExchangeRate(CreateExchangeRateCommand cmd) {
        return exchangeRepository.find(cmd.getDate(), cmd.getCurrency());
    }

}
