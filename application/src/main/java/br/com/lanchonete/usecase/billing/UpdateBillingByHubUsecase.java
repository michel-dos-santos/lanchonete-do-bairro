package br.com.lanchonete.usecase.billing;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.port.repository.BillingRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.billing.UpdateBillingByHub;

import java.util.UUID;

public class UpdateBillingByHubUsecase implements UpdateBillingByHub {

    private final LogRepository logRepository;
    private final BillingRepository billingRepository;
    private final OrderRepository orderRepository;

    public UpdateBillingByHubUsecase(LogRepository logRepository, BillingRepository billingRepository, OrderRepository orderRepository) {
        this.logRepository = logRepository;
        this.billingRepository = billingRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void updateStatusPaymentType(StatusPaymentType statusPaymentType, UUID id) {
        logRepository.info(UpdateBillingByHubUsecase.class, LogCode.LogCodeInfo._0035);
        billingRepository.updateStatusPaymentType(id, statusPaymentType);

        if (StatusPaymentType.PAID == statusPaymentType) {
            orderRepository.updateStatus(orderRepository.findByBillingId(id).getId(), StatusType.IN_PREPARATION);
        }
        logRepository.info(UpdateBillingByHubUsecase.class, LogCode.LogCodeInfo._0036);
    }
}
