package br.com.lanchonete.configuration;

import br.com.lanchonete.hub.itau.NotifyBillingHubItauRepository;
import br.com.lanchonete.hub.mercadopago.NotifyBillingHubMercadoPagoRepository;
import br.com.lanchonete.port.repository.BillingRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.NotifyBillingHubRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.billing.NotifyBillingHub;
import br.com.lanchonete.usecase.billing.GenerateBillingUsecase;
import br.com.lanchonete.usecase.billing.NotifyBillingHubUsecase;
import br.com.lanchonete.usecase.billing.UpdateBillingByHubUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class BillingBeanConfiguration {

    @Bean
    GenerateBillingUsecase generateBilling(LogRepository logRepository, BillingRepository billingRepository, NotifyBillingHub notifyBillingHub) {
        return new GenerateBillingUsecase(logRepository, billingRepository, notifyBillingHub);
    }

    @Bean
    NotifyBillingHubUsecase notifyBillingHub(LogRepository logRepository, NotifyBillingHubMercadoPagoRepository notifyBillingHubMercadoPagoRepository, NotifyBillingHubItauRepository notifyBillingHubItauRepository) {
        List<NotifyBillingHubRepository> billingHubRepositories = Arrays.asList(notifyBillingHubMercadoPagoRepository, notifyBillingHubItauRepository);
        return new NotifyBillingHubUsecase(logRepository, billingHubRepositories);
    }

    @Bean
    UpdateBillingByHubUsecase updateBillingByHub(LogRepository logRepository, BillingRepository billingRepository, OrderRepository orderRepository) {
        return new UpdateBillingByHubUsecase(logRepository, billingRepository, orderRepository);
    }

}
