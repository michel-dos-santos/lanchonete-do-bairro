package br.com.lanchonete.exception;

public class ClientInvalidException extends RuntimeException {

    public ClientInvalidException(String field, String content) {
        super(String.format("Cliente inválido com base no %s: %s", field, content));
    }

}