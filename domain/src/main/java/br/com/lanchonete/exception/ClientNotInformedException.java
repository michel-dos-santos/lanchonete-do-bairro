package br.com.lanchonete.exception;

public class ClientNotInformedException extends RuntimeException {

    public ClientNotInformedException() {
        super(String.format("Cliente não informado"));
    }

}