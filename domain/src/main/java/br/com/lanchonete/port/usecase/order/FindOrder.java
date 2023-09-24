package br.com.lanchonete.port.usecase.order;

import br.com.lanchonete.model.Order;

import java.util.List;

public interface FindOrder {

    List<Order> findAll();
    List<Order> findAllReceived();
    List<Order> findAllInPreparation();
    List<Order> findAllReady();
    List<Order> findAllFinished();

}
