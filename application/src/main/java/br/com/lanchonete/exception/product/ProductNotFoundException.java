package br.com.lanchonete.exception.product;

import java.util.Objects;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String field1, String field2, String content1, String content2) {
        super(Objects.isNull(field2) ? String.format("Produto não encontrado com base no %s: %s", field1, content1) : String.format("Produto não encontrado com base no %s: %s e %s: %s", field1, field2, content1, content2));
    }

}