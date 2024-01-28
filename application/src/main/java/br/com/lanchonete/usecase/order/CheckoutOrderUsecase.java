package br.com.lanchonete.usecase.order;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.billing.GenerateBilling;
import br.com.lanchonete.port.usecase.order.CheckoutOrder;
import br.com.lanchonete.port.usecase.order.ValidateCheckoutOrder;

public class CheckoutOrderUsecase implements CheckoutOrder {

    private final LogRepository logRepository;
    private final OrderRepository orderRepository;
    private final ValidateCheckoutOrder validateCheckoutOrder;
    private final GenerateBilling generateBilling;

    public CheckoutOrderUsecase(LogRepository logRepository, OrderRepository orderRepository, ValidateCheckoutOrder validateCheckoutOrder, GenerateBilling generateBilling) {
        this.logRepository = logRepository;
        this.orderRepository = orderRepository;
        this.validateCheckoutOrder = validateCheckoutOrder;
        this.generateBilling = generateBilling;
    }

    @Override
    public Order checkout(Order order) {
        logRepository.info(CheckoutOrderUsecase.class, LogCode.LogCodeInfo._0022);
        order.setStatus(StatusType.RECEIVED);
        validateCheckoutOrder.validate(order);
        order.setBilling(generateBilling.generate(order.getBilling()));
        Order orderCheckout = orderRepository.checkout(order);

        orderRepository.updateStatus(orderCheckout.getId(), StatusType.IN_BILLING);
        logRepository.info(CheckoutOrderUsecase.class, LogCode.LogCodeInfo._0023);
        return orderCheckout;
    }
}
