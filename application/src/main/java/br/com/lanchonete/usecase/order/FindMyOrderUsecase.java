package br.com.lanchonete.usecase.order;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.order.FindMyOrder;

import java.util.UUID;

public class FindMyOrderUsecase implements FindMyOrder {

    private final LogRepository logRepository;
    private final OrderRepository orderRepository;

    public FindMyOrderUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        this.logRepository = logRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findMyOrder(UUID id) {
        logRepository.info(FindMyOrderUsecase.class, LogCode.LogCodeInfo._0038);
        Order order = orderRepository.findById(id);
        logRepository.info(FindMyOrderUsecase.class, LogCode.LogCodeInfo._0039);
        return order;
    }
}
