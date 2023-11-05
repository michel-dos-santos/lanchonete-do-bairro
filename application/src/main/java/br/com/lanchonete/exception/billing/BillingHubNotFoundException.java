package br.com.lanchonete.exception.billing;

public class BillingHubNotFoundException extends RuntimeException {

    public BillingHubNotFoundException() {
        super(String.format("Metodo de Pagamento não encontrado"));
    }

}