package br.com.lanchonete.repository.postgres.entity;

import java.util.Arrays;

public enum StatusPaymentType {
    PENDING_PAYMENT,
    PAID;

    public static StatusPaymentType get(String type) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

}
