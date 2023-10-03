package br.com.lanchonete.exception.client;

public class ClientFoundException extends RuntimeException {

    public ClientFoundException(String field, String content) {
        super(String.format("Cliente já existente com base no %s: %s", field, content));
    }

}