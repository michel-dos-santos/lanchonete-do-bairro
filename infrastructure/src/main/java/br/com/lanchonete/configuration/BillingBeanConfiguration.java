package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.BillingRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.usecase.billing.GenerateBillingUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingBeanConfiguration {

    @Bean
    GenerateBillingUsecase makeBilling(LogRepository logRepository, BillingRepository billingRepository) {
        return new GenerateBillingUsecase(logRepository, billingRepository);
    }

}
