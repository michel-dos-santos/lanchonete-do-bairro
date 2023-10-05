package br.com.lanchonete.exception.category;

public class CategoryInvalidException extends RuntimeException {

    public CategoryInvalidException(String field, String content) {
        super(String.format("Categoria inválida com base no %s: %s", field, content));
    }

}