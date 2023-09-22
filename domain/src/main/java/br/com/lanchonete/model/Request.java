package br.com.lanchonete.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Request {

    private UUID id;
    private Date createdAt;
    private Date updatedAt;
    private String number;
    private StatusType status;
    private Billing billing;
    private List<RequestItem> requestItems;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public List<RequestItem> getRequestItems() {
        return requestItems;
    }

    public void setRequestItems(List<RequestItem> requestItems) {
        this.requestItems = requestItems;
    }
}
