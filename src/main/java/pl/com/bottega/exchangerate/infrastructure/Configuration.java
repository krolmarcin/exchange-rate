package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.exchangerate.api.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.api.ExchangeCatalog;
import pl.com.bottega.exchangerate.api.impl.StandardExchangeRatePanel;
import pl.com.bottega.exchangerate.api.ExchangeRatePanel;
import pl.com.bottega.exchangerate.api.impl.StandardExchangeRateCalculator;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ExchangeRepository exchangeRepository() {
        return new JPAExchangeRateRepository();
    }

    @Bean
    public ExchangeCatalog exchangeCatalog() {
        return new JPAExchangeCatalog();
    }

    @Bean
    public ExchangeRateCalculator exchangeCalculator(ExchangeRepository exchangeRepository, ExchangeCatalog exchangeCatalog) {
        return new StandardExchangeRateCalculator(exchangeRepository, exchangeCatalog);
    }

    @Bean
    public ExchangeRatePanel adminPanel(ExchangeRepository exchangeRepository) {
        return new StandardExchangeRatePanel(exchangeRepository);
    }

}
