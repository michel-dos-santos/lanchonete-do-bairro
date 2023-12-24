package br.com.lanchonete.port.usecase.order;

import br.com.lanchonete.model.StatusType;

import java.util.UUID;

public interface UpdateStatusOrder {

    void updateStatusOrder(UUID id, StatusType statusType);

}
