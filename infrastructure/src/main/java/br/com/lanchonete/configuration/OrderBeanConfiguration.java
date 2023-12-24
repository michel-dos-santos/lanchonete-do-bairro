package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.billing.GenerateBilling;
import br.com.lanchonete.port.usecase.order.ValidateCheckoutOrder;
import br.com.lanchonete.usecase.order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderBeanConfiguration {

    @Bean
    CheckoutOrderUsecase checkoutOrder(LogRepository logRepository, OrderRepository orderRepository, ValidateCheckoutOrder validateCheckoutOrder, GenerateBilling generateBilling) {
        return new CheckoutOrderUsecase(logRepository, orderRepository, validateCheckoutOrder, generateBilling);
    }
    @Bean
    ValidateCheckoutOrderUsecase validateCheckoutOrder(LogRepository logRepository, OrderRepository orderRepository) {
        return new ValidateCheckoutOrderUsecase(logRepository, orderRepository);
    }

    @Bean
    FindAllOrdersByStatusUsecase findAllOrdersByStatus(LogRepository logRepository, OrderRepository orderRepository) {
        return new FindAllOrdersByStatusUsecase(logRepository, orderRepository);
    }

    @Bean
    FindMyOrderUsecase findMyOrderUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        return new FindMyOrderUsecase(logRepository, orderRepository);
    }

    @Bean
    FindStatusPaymentMyOrderUsecase findStatusPaymentMyOrderUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        return new FindStatusPaymentMyOrderUsecase(logRepository, orderRepository);
    }

    @Bean
    UpdateStatusOrderUsecase updateStatusOrderUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        return new UpdateStatusOrderUsecase(logRepository, orderRepository);
    }
}
