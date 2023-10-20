package br.com.lanchonete.model;

import java.util.UUID;

public class BillingForm {

    private UUID id;
    private BillingFormType billingFormType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BillingFormType getBillingFormType() {
        return billingFormType;
    }

    public void setBillingFormType(BillingFormType billingFormType) {
        this.billingFormType = billingFormType;
    }
}
