package br.com.lanchonete.exception.order;

import java.util.Objects;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String field1, String field2, String content1, String content2) {
        super(Objects.isNull(field2) ? String.format("Pedido não encontrado com base no %s: %s", field1, content1) : String.format("Pedido não encontrado com base no %s: %s e %s: %s", field1, field2, content1, content2));
    }

}