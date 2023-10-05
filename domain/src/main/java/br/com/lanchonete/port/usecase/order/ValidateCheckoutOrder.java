package br.com.lanchonete.port.usecase.order;

import br.com.lanchonete.model.Order;

public interface ValidateCheckoutOrder {

    void validate(Order order);

}
