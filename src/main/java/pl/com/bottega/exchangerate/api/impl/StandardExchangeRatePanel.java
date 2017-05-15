package pl.com.bottega.exchangerate.api.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.api.ExchangeRatePanel;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

@Transactional
public class StandardExchangeRatePanel implements ExchangeRatePanel {

    private ExchangeRepository exchangeRepository;

    public StandardExchangeRatePanel(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public void createExchangeRate(CreateExchangeRateCommand cmd) {
        ExchangeRate exchangeRate = new ExchangeRate(cmd);

        if (exchangeRepository.exist(cmd.getDate(), cmd.getCurrency())) {
            exchangeRepository.update(exchangeRate);
        } else {
            exchangeRepository.put(exchangeRate);
        }
    }

}
