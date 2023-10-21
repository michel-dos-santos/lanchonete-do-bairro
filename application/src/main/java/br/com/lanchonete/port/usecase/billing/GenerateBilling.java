package br.com.lanchonete.port.usecase.billing;

import br.com.lanchonete.model.Billing;

public interface GenerateBilling {

    Billing generate(Billing billing);

}
