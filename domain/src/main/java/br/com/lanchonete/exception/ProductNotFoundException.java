package br.com.lanchonete.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String field1, String field2, String content1, String content2) {
        super(String.format("Produto não encontrado com base no %s: %s e %s: %s", field1, field2, content1, content2));
    }

}