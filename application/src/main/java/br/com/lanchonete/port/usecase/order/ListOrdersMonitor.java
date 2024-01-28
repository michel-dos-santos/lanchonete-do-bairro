package br.com.lanchonete.port.usecase.order;

import br.com.lanchonete.model.Order;

import java.util.List;
import java.util.UUID;

public interface ListOrdersMonitor {

    List<Order> listOrdersMonitor();

}
