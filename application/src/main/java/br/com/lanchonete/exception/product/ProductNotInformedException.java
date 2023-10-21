package br.com.lanchonete.exception.product;

public class ProductNotInformedException extends RuntimeException {

    public ProductNotInformedException() {
        super(String.format("Produto não informado"));
    }

}