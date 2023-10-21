package br.com.lanchonete.port.usecase.order;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;

import java.util.List;

public interface FindAllOrdersByStatus {

    List<Order> findAll(StatusType statusType);

}
