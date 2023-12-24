package br.com.lanchonete.usecase.order;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.order.FindStatusPaymentMyOrder;

import java.util.UUID;

public class FindStatusPaymentMyOrderUsecase implements FindStatusPaymentMyOrder {

    private final LogRepository logRepository;
    private final OrderRepository orderRepository;

    public FindStatusPaymentMyOrderUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        this.logRepository = logRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public StatusPaymentType findStatusPaymentMyOrder(UUID id) {
        logRepository.info(FindStatusPaymentMyOrderUsecase.class, LogCode.LogCodeInfo._0040);
        Order order = orderRepository.findById(id);
        logRepository.info(FindStatusPaymentMyOrderUsecase.class, LogCode.LogCodeInfo._0041);
        return order.getBilling().getStatus();
    }
}
