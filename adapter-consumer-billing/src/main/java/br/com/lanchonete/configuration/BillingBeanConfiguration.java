package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.BillingRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.usecase.billing.UpdateBillingByHubUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingBeanConfiguration {

    @Bean
    UpdateBillingByHubUsecase updateBillingByHub(LogRepository logRepository, BillingRepository billingRepository, OrderRepository orderRepository) {
        return new UpdateBillingByHubUsecase(logRepository, billingRepository, orderRepository);
    }

}
