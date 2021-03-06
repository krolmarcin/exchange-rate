package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.exchangerate.api.ExchangeRateCalculator;
import pl.com.bottega.exchangerate.api.ExchangeCatalog;
import pl.com.bottega.exchangerate.api.impl.StandardExchangeRateManager;
import pl.com.bottega.exchangerate.api.ExchangeRateManager;
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
    public ExchangeRateCalculator exchangeCalculator(ExchangeCatalog exchangeCatalog) {
        return new StandardExchangeRateCalculator(exchangeCatalog);
    }

    @Bean
    public ExchangeRateManager exchangeRateManager(ExchangeRepository exchangeRepository) {
        return new StandardExchangeRateManager(exchangeRepository);
    }

}
