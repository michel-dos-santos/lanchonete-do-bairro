package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order checkout(Order order);
    List<Order> findAllOrdersByStatus(StatusType statusType);

    Order updateStatus(UUID id, StatusType statusType);

    Order findById(UUID id);

    Order findByBillingId(UUID id);

    Order save(Order order);

    List<Order> listOrdersMonitor();

}
