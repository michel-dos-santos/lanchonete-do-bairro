package br.com.lanchonete.model;

import java.util.Arrays;

public enum StatusPaymentType {
    PENDING_PAYMENT("Pendente de Pagamento"),
    PAID("Pago");

    private String description;

    StatusPaymentType(String description) {
        this.description = description;
    }

    public static StatusPaymentType get(String type) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    public String getDescription() {
        return this.description;
    }

}
