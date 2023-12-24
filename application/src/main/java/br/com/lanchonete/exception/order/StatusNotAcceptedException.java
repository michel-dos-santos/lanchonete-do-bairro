package br.com.lanchonete.exception.order;

public class StatusNotAcceptedException extends RuntimeException {

    public StatusNotAcceptedException(String statusA, String statusB) {
        super(String.format("Status %s não pode ser usado em um pedido com status %s", statusA, statusB));
    }

}