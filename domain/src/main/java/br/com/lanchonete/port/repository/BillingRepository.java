package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Billing;

public interface BillingRepository {

    Billing generate(Billing billing);

}
