package br.com.lanchonete.exception;

public class CategoryInvalidException extends RuntimeException {

    public CategoryInvalidException(String field, String content) {
        super(String.format("Categoria inválida com base no %s: %s", field, content));
    }

}