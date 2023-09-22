package br.com.lanchonete.model;

import java.util.UUID;

public class BillingForm {

    private UUID id;
    private String identification;
    private BillingFormType billingFormType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public BillingFormType getBillingFormType() {
        return billingFormType;
    }

    public void setBillingFormType(BillingFormType billingFormType) {
        this.billingFormType = billingFormType;
    }
}
