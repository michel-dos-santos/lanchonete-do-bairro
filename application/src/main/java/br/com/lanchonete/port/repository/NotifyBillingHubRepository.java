package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.BillingFormType;

public interface NotifyBillingHubRepository {

    BillingFormType getFormType();

    void sendNotification(Billing billing);

}
