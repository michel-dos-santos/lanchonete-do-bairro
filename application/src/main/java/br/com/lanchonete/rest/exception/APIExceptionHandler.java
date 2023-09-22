package br.com.lanchonete.rest.exception;

import br.com.lanchonete.exception.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<Object> handleApiException(APIException ex, WebRequest request){
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.valueOf(ex.getStatusCode()), request );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Map<String, String>> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            Map<String, String> errorMap = new HashMap<>();
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMap.put(fieldName, message);
            errors.add(errorMap);
        });

        HttpStatus httpStatus = HttpStatus.resolve(status.value());
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.name(), httpStatus.value(), httpStatus.getReasonPhrase(), errors);
        return new ResponseEntity<Object>(errorResponse, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.resolve(status.value());
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.name(), httpStatus.value(), httpStatus.getReasonPhrase(), null);
        return new ResponseEntity<Object>(errorResponse, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String description = ex.getClass().getSimpleName();
        List<?> errors = new ArrayList<>();

        if (ex instanceof APIException) {
            description = ((APIException) ex).getDescription();
            errors = ((APIException) ex).getErrors();
        }

        HttpStatus httpStatus = HttpStatus.resolve(status.value());
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.name(), httpStatus.value(), description, errors);
        return new ResponseEntity<Object>(errorResponse, httpStatus);
    }
}