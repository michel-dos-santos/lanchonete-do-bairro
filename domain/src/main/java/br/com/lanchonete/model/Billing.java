package br.com.lanchonete.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Billing {

    private UUID id;
    private Date createdAt;
    private Date updatedAt;
    private BigDecimal totalPrice;
    private StatusPaymentType status;
    private BillingForm billingForm;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StatusPaymentType getStatus() {
        return status;
    }

    public void setStatus(StatusPaymentType status) {
        this.status = status;
    }

    public BillingForm getBillingForm() {
        return billingForm;
    }

    public void setBillingForm(BillingForm billingForm) {
        this.billingForm = billingForm;
    }
}
