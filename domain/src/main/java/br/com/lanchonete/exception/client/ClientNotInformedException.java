package br.com.lanchonete.exception.client;

public class ClientNotInformedException extends RuntimeException {

    public ClientNotInformedException() {
        super(String.format("Cliente não informado"));
    }

}