package br.com.lanchonete.usecase.order;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.order.FindAllOrdersByStatus;

import java.util.List;

public class FindAllOrdersByStatusUsecase implements FindAllOrdersByStatus {

    private final LogRepository logRepository;
    private final OrderRepository orderRepository;

    public FindAllOrdersByStatusUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        this.logRepository = logRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll(StatusType statusType) {
        logRepository.info(FindAllOrdersByStatusUsecase.class, LogCode.LogCodeInfo._0026);
        List<Order> orders = orderRepository.findAllOrdersByStatus(statusType);
        logRepository.info(FindAllOrdersByStatusUsecase.class, LogCode.LogCodeInfo._0027);
        return orders;
    }
}
