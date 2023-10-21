package br.com.lanchonete.rest.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class APIException extends Exception {

    private final String status;
    private final Integer statusCode;
    private final String description;
    private final List<?> errors;

    public APIException(String status, Integer statusCode, String description, List<?> errors) {
        super(description);
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

    public static APIException badRequest(String description, List<?> errors) {
        return new APIException(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), description, errors);
    }

    public static APIException notFound(String description, List<?> errors) {
        return new APIException(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), description, errors);
    }

    public static APIException internalError(String description, List<?> errors) {
        return new APIException(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value(), description, errors);
    }

}