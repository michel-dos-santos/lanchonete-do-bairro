package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Order;

public interface OrderRepository {

    Order checkout(Order order);

}
