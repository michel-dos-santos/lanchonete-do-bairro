package br.com.lanchonete.port.usecase.order;

import br.com.lanchonete.model.Order;

import java.util.UUID;

public interface FindMyOrder {

    Order findMyOrder(UUID id);

}
