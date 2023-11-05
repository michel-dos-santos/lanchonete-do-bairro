package br.com.lanchonete.port.usecase.billing;

import br.com.lanchonete.model.Billing;

public interface NotifyBillingHub {

    void notify(Billing billing);

}
