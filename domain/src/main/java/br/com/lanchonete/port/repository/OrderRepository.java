package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;

import java.util.List;

public interface OrderRepository {

    Order checkout(Order order);
    List<Order> findAllOrdersByStatus(StatusType statusType);

}
