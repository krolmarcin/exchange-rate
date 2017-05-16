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
        ExchangeRate exchangeRate = new ExchangeRate(cmd);
        if (exchangeRepository.exist(cmd.getDate(), cmd.getCurrency())) {
            ExchangeRate eR = exchangeRepository.find(cmd.getDate(), cmd.getCurrency());
            eR.setRate(cmd.getRate());
            exchangeRepository.update(eR);
        } else {
            exchangeRepository.put(exchangeRate);
        }
    }

}
