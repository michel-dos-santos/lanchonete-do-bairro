package br.com.lanchonete.hub.mercadopago;

import br.com.lanchonete.hub.base.BillingControllerBase;
import br.com.lanchonete.hub.base.exception.APIException;
import br.com.lanchonete.hub.mercadopago.dto.MercadoPagoRequestDTO;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.LogRepository;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Endpoint Billing Provider Mercado Pago")
@Validated
@RestController
@RequestMapping(path = BillingControllerBase.BASE_PATH+ MercadoPagoController.BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class MercadoPagoController extends BillingControllerBase {

    public static final String BASE_PATH = "/mercado-pago";

    @Autowired
    private LogRepository logRepository;

    @Counted(value = "execution.count.updateBillingByMercadoPago")
    @Timed(value = "execution.time.updateBillingByMercadoPago", longTask = true)
    @PutMapping
    public void updateBillingByMercadoPago(@RequestBody @Valid MercadoPagoRequestDTO mercadoPagoRequestDTO) throws APIException {
        logRepository.info(MercadoPagoController.class, LogCode.LogCodeInfo._0037);

        updateBillingBy(mercadoPagoRequestDTO.getStatusPaymentType(), mercadoPagoRequestDTO.getId());
    }
}
