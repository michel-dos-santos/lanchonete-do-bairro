package br.com.lanchonete.model;

import java.util.Arrays;

public enum StatusType {
    RECEIVED("Recebido"),
    IN_BILLING("Em cobrança"),
    IN_PREPARATION("Em preparação"),
    READY("Pronto"),
    FINISHED("Finalizado");

    private String description;

    StatusType(String description) {
        this.description = description;
    }

    public static StatusType get(String type) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    public String getDescription() {
        return this.description;
    }

}
