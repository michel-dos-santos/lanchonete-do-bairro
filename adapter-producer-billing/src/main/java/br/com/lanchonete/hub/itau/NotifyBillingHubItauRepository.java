package br.com.lanchonete.hub.itau;

import br.com.lanchonete.hub.itau.dto.ItauRequestDTO;
import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.BillingFormType;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.NotifyBillingHubRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifyBillingHubItauRepository implements NotifyBillingHubRepository {

    @Autowired
    private ItauServiceFeignClient itauServiceFeignClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;

    @Override
    public BillingFormType getFormType() {
        return BillingFormType.PIX_ITAU;
    }

    @Override
    public void sendNotification(Billing billing) {
        logRepository.info(NotifyBillingHubItauRepository.class, LogCode.LogCodeInfo._0032);
        itauServiceFeignClient.billing(new ItauRequestDTO(billing.getId().toString(), billing.getTotalPrice()));
        logRepository.info(NotifyBillingHubItauRepository.class, LogCode.LogCodeInfo._0033);
    }

}