package br.com.lanchonete.port.usecase.billing;

import br.com.lanchonete.model.StatusPaymentType;

import java.util.UUID;

public interface UpdateBillingByHub {

    void updateStatusPaymentType(StatusPaymentType statusPaymentType, UUID id);

}
