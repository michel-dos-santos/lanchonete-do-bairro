package br.com.lanchonete.exception;

public class ProductActivedException extends RuntimeException {

    public ProductActivedException() {
        super("Produto encontra-se ativado, portanto não será removido");
    }

}