package br.com.lanchonete.model;

public class BillingForm {

    private String identification;
    private BillingFormType billingFormType;

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
