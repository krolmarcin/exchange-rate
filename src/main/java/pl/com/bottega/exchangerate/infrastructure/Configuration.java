package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.exchangerate.api.impl.StandardAdminPanel;
import pl.com.bottega.exchangerate.domain.AdminPanel;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ExchangeRepository exchangeRepository() {
        return new JPAExchangeRateRepository();
    }

    @Bean
    public AdminPanel adminPanel(ExchangeRepository exchangeRepository) {
        return new StandardAdminPanel(exchangeRepository);
    }

}
