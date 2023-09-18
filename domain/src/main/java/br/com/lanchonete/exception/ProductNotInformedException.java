package br.com.lanchonete.exception;

public class ProductNotInformedException extends RuntimeException {

    public ProductNotInformedException() {
        super(String.format("Produto não informado"));
    }

}