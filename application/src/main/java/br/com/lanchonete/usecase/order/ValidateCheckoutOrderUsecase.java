package br.com.lanchonete.usecase.order;

import br.com.lanchonete.exception.order.OrderInvalidException;
import br.com.lanchonete.exception.order.OrderNotInformedException;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.order.ValidateCheckoutOrder;

import java.util.Objects;

public class ValidateCheckoutOrderUsecase implements ValidateCheckoutOrder {

    private final LogRepository logRepository;
    private final OrderRepository orderRepository;

    public ValidateCheckoutOrderUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        this.logRepository = logRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void validate(Order order) {
        logRepository.info(ValidateCheckoutOrderUsecase.class, LogCode.LogCodeInfo._0021);

        if (Objects.isNull(order)) {
            throw new OrderNotInformedException();
        }
        if (Objects.isNull(order.getOrderItems()) || order.getOrderItems().isEmpty()) {
            throw new OrderInvalidException("orderItems", ""+order.getOrderItems());
        }
        if (Objects.isNull(order.getBilling())) {
            throw new OrderInvalidException("billing", ""+order.getBilling());
        }
    }
}
