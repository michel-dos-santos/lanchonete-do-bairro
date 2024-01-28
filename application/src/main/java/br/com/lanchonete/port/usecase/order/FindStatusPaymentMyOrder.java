package br.com.lanchonete.port.usecase.order;

import br.com.lanchonete.model.StatusPaymentType;

import java.util.UUID;

public interface FindStatusPaymentMyOrder {

    StatusPaymentType findStatusPaymentMyOrder(UUID id);

}
