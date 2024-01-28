package br.com.lanchonete.usecase.order;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.order.ListOrdersMonitor;

import java.util.List;

public class ListOrdersMonitorUsecase implements ListOrdersMonitor {

    private final LogRepository logRepository;
    private final OrderRepository orderRepository;

    public ListOrdersMonitorUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        this.logRepository = logRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> listOrdersMonitor() {
        logRepository.info(ListOrdersMonitorUsecase.class, LogCode.LogCodeInfo._0044);
        List<Order> orders = orderRepository.listOrdersMonitor();
        logRepository.info(ListOrdersMonitorUsecase.class, LogCode.LogCodeInfo._0045);
        return orders;
    }
}
