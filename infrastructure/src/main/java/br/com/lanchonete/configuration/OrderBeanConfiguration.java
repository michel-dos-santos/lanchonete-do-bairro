package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.billing.GenerateBilling;
import br.com.lanchonete.port.usecase.order.ValidateCheckoutOrder;
import br.com.lanchonete.usecase.order.CheckoutOrderUsecase;
import br.com.lanchonete.usecase.order.ValidateCheckoutOrderUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderBeanConfiguration {

    @Bean
    CheckoutOrderUsecase checkoutOrder(LogRepository logRepository, OrderRepository orderRepository, ValidateCheckoutOrder validateCheckoutOrder, GenerateBilling generateBilling) {
        return new CheckoutOrderUsecase(logRepository, orderRepository, validateCheckoutOrder, generateBilling);
    }
    @Bean
    ValidateCheckoutOrder validateCheckoutOrder(LogRepository logRepository, OrderRepository orderRepository) {
        return new ValidateCheckoutOrderUsecase(logRepository, orderRepository);
    }

}
