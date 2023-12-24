package br.com.lanchonete.usecase.order;

import br.com.lanchonete.exception.order.StatusNotAcceptedException;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.order.UpdateStatusOrder;

import java.util.*;

public class UpdateStatusOrderUsecase implements UpdateStatusOrder {

    private final LogRepository logRepository;
    private final OrderRepository orderRepository;

    public UpdateStatusOrderUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        this.logRepository = logRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void updateStatusOrder(UUID id, StatusType statusType) {
        logRepository.info(UpdateStatusOrderUsecase.class, LogCode.LogCodeInfo._0042);
        Order order = orderRepository.findById(id);
        Map<StatusType, List<StatusType>> mapStatusAccepted = Map.ofEntries(
                new AbstractMap.SimpleEntry<>(StatusType.RECEIVED, Arrays.asList(StatusType.IN_BILLING, StatusType.IN_PREPARATION, StatusType.READY, StatusType.FINISHED)),
                new AbstractMap.SimpleEntry<>(StatusType.IN_BILLING, Arrays.asList(StatusType.IN_PREPARATION, StatusType.READY, StatusType.FINISHED)),
                new AbstractMap.SimpleEntry<>(StatusType.IN_PREPARATION, Arrays.asList(StatusType.READY, StatusType.FINISHED)),
                new AbstractMap.SimpleEntry<>(StatusType.READY, List.of(StatusType.FINISHED))
        );

        List<StatusType> statusTypesAccepted = mapStatusAccepted.get(order.getStatus());
        if (Objects.isNull(statusTypesAccepted) || !statusTypesAccepted.contains(statusType)) {
            throw new StatusNotAcceptedException(statusType.getDescription(), order.getStatus().getDescription());
        }

        order.setStatus(statusType);
        orderRepository.save(order);
        logRepository.info(UpdateStatusOrderUsecase.class, LogCode.LogCodeInfo._0043);
    }
}
