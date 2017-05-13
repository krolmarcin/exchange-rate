package pl.com.bottega.exchangerate.api.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.exchangerate.domain.AdminPanel;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

@Transactional
public class StandardAdminPanel implements AdminPanel {

    private ExchangeRepository exchangeRepository;

    public StandardAdminPanel(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public void createExchangeRate(CreateExchangeRateCommand cmd) {
        ExchangeRate exchangeRate = new ExchangeRate(cmd);
        exchangeRepository.put(exchangeRate);
    }

}
