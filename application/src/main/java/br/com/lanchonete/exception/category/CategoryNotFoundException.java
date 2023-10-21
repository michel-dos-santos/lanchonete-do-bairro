package br.com.lanchonete.exception.category;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String field, String content) {
        super(String.format("Categoria não encontrado com base no %s: %s", field, content));
    }

}