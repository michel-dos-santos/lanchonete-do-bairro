package br.com.lanchonete.postgres.entity;

import java.util.Arrays;

public enum StatusType {
    RECEIVED,
    IN_BILLING,
    IN_PREPARATION,
    READY,
    FINISHED;

    public static StatusType get(String type) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

}
