package br.com.lanchonete.exception;

public class CategoryNotInformedException extends RuntimeException {

    public CategoryNotInformedException() {
        super(String.format("Categoria não informado"));
    }

}