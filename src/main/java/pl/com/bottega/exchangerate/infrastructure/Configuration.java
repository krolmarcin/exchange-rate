package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.exchangerate.api.ExchangeCalculator;
import pl.com.bottega.exchangerate.api.impl.StandardAdminPanel;
import pl.com.bottega.exchangerate.api.AdminPanel;
import pl.com.bottega.exchangerate.api.impl.StandardExchangeCalculator;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ExchangeRepository exchangeRepository() {
        return new JPAExchangeRateRepository();
    }

    @Bean
    public ExchangeCalculator exchangeCalculator(ExchangeRepository exchangeRepository) {
        return new StandardExchangeCalculator(exchangeRepository);
    }

    @Bean
    public AdminPanel adminPanel(ExchangeRepository exchangeRepository) {
        return new StandardAdminPanel(exchangeRepository);
    }

}
