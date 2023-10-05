package br.com.lanchonete.exception.order;

public class OrderNotInformedException extends RuntimeException {

    public OrderNotInformedException() {
        super(String.format("Pedido não informado"));
    }

}