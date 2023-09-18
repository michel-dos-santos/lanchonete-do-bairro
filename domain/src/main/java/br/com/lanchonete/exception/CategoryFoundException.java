package br.com.lanchonete.exception;

public class CategoryFoundException extends RuntimeException {

    public CategoryFoundException(String field, String content) {
        super(String.format("Categoria já existente com base no %s: %s", field, content));
    }

}