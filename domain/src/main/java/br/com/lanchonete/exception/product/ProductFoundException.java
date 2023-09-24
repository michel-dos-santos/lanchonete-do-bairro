package br.com.lanchonete.exception.product;

public class ProductFoundException extends RuntimeException {

    public ProductFoundException(String field1, String field2, String content1, String content2) {
        super(String.format("Produto já existente com base no %s: %s e %s: %s", field1, content1, field2, content2));
    }

}