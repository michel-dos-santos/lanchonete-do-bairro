package br.com.lanchonete.usecase.billing;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.port.repository.BillingRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.billing.GenerateBilling;
import br.com.lanchonete.port.usecase.billing.NotifyBillingHub;

public class GenerateBillingUsecase implements GenerateBilling {

    private final LogRepository logRepository;
    private final BillingRepository billingRepository;
    private final NotifyBillingHub notifyBillingHub;

    public GenerateBillingUsecase(LogRepository logRepository, BillingRepository billingRepository, NotifyBillingHub notifyBillingHub) {
        this.logRepository = logRepository;
        this.billingRepository = billingRepository;
        this.notifyBillingHub = notifyBillingHub;
    }

    @Override
    public Billing generate(Billing billing) {
        logRepository.info(GenerateBillingUsecase.class, LogCode.LogCodeInfo._0024);
        billing.setStatus(StatusPaymentType.PENDING_PAYMENT);
        billing = billingRepository.save(billing);
        notifyBillingHub.notify(billing);
        logRepository.info(GenerateBillingUsecase.class, LogCode.LogCodeInfo._0025);
        return billing;
    }
}
