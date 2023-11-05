package br.com.lanchonete.hub.mercadopago;

import br.com.lanchonete.hub.mercadopago.dto.MercadoPagoRequestDTO;
import br.com.lanchonete.hub.mercadopago.dto.MercadoPagoResponseDTO;
import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.BillingFormType;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.NotifyBillingHubRepository;
import br.com.lanchonete.usecase.billing.GenerateBillingUsecase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifyBillingHubMercadoPagoRepository implements NotifyBillingHubRepository {

    @Autowired
    private MercadoPagoServiceFeignClient mercadoPagoServiceFeignClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;

    @Override
    public BillingFormType getFormType() {
        return BillingFormType.QRCODE_MERCADO_PAGO;
    }

        @Override
    public void sendNotification(Billing billing) {
        logRepository.info(NotifyBillingHubMercadoPagoRepository.class, LogCode.LogCodeInfo._0030);
        mercadoPagoServiceFeignClient.billing(new MercadoPagoRequestDTO(billing.getId().toString(), billing.getTotalPrice()));
        logRepository.info(NotifyBillingHubMercadoPagoRepository.class, LogCode.LogCodeInfo._0031);
    }

}