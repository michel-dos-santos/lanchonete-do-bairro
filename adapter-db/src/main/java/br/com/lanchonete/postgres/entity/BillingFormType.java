package br.com.lanchonete.postgres.entity;

import java.util.Arrays;

public enum BillingFormType {
    QRCODE_MERCADO_PAGO,
    PIX_ITAU;

    public static BillingFormType get(String type) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

}
