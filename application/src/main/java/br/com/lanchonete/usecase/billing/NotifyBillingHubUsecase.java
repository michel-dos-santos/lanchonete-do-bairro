package br.com.lanchonete.usecase.billing;

import br.com.lanchonete.exception.billing.BillingHubNotFoundException;
import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.BillingFormType;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.NotifyBillingHubRepository;
import br.com.lanchonete.port.usecase.billing.NotifyBillingHub;

import java.util.List;
import java.util.Objects;

public class NotifyBillingHubUsecase implements NotifyBillingHub {

    private final LogRepository logRepository;
    private final List<NotifyBillingHubRepository> billingHubRepositories;

    public NotifyBillingHubUsecase(LogRepository logRepository, List<NotifyBillingHubRepository> billingHubRepositories) {
        this.logRepository = logRepository;
        this.billingHubRepositories = billingHubRepositories;
    }

    @Override
    public void notify(Billing billing) {
        logRepository.info(NotifyBillingHubUsecase.class, LogCode.LogCodeInfo._0028);
        //existem duas opções fazer um broadcast ou fazer o condicional como sendo regra interna de negocio

        NotifyBillingHubRepository hub = getSpecificBillingHubInstance(billing.getBillingForm().getBillingFormType());
        if (Objects.isNull(hub)) {
            throw new BillingHubNotFoundException();
        }

        hub.sendNotification(billing);
        logRepository.info(NotifyBillingHubUsecase.class, LogCode.LogCodeInfo._0029);
    }

    private NotifyBillingHubRepository getSpecificBillingHubInstance(BillingFormType billingFormType) {
        for (NotifyBillingHubRepository hub: billingHubRepositories) {
            if (billingFormType == hub.getFormType()) {
                return hub;
            }
        }

        return null;
    }
}
