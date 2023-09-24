package br.com.lanchonete.exception.order;

public class OrderInvalidException extends RuntimeException {

    public OrderInvalidException(String field, String content) {
        super(String.format("Pedido inválido com base no %s: %s", field, content));
    }

}