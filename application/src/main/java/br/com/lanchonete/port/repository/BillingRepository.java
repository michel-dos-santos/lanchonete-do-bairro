package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.StatusPaymentType;

import java.util.UUID;

public interface BillingRepository {

    Billing save(Billing billing);

    Billing updateStatusPaymentType(UUID id, StatusPaymentType status);

    Billing findById(UUID id);

}
