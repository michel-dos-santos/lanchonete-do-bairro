package br.com.lanchonete.exception;

import java.util.List;

public class ErrorResponse {

    private String status;
    private Integer statusCode;
    private String description;
    private List<?> errors;

    public ErrorResponse(String status, Integer statusCode, String description, List<?> errors) {
        this.status = status;
        this.statusCode = statusCode;
        this.description = description;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }

    public List<?> getErrors() {
        return errors;
    }
}
