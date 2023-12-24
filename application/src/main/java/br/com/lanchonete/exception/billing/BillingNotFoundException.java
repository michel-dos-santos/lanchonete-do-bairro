package br.com.lanchonete.exception.billing;

import java.util.Objects;

public class BillingNotFoundException extends RuntimeException {

    public BillingNotFoundException(String field1, String field2, String content1, String content2) {
        super(Objects.isNull(field2) ? String.format("Billing não encontrado com base no %s: %s", field1, content1) : String.format("Billing não encontrado com base no %s: %s e %s: %s", field1, field2, content1, content2));
    }

}