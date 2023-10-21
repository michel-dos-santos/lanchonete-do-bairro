package br.com.lanchonete.model;

import java.util.Arrays;

public enum StatusActiveType {
    ACTIVE("Ativo"),
    INACTIVE("Inativo");

    private String description;

    StatusActiveType(String description) {
        this.description = description;
    }

    public static StatusActiveType get(String type) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    public String getDescription() {
        return this.description;
    }

}
