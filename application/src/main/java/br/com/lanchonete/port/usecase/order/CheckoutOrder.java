package br.com.lanchonete.port.usecase.order;

import br.com.lanchonete.model.Order;

public interface CheckoutOrder {

    Order checkout(Order order);

}
