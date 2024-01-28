package br.com.lanchonete.model;

import java.util.Arrays;

public enum BillingFormType {
    QRCODE_MERCADO_PAGO("QRCode Mercado Pago"),
    PIX_ITAU("PIX Itaú");

    private String description;

    BillingFormType(String description) {
        this.description = description;
    }

    public static BillingFormType get(String type) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    public String getDescription() {
        return this.description;
    }

}
