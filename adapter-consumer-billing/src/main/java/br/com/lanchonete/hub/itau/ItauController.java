package br.com.lanchonete.hub.itau;

import br.com.lanchonete.hub.base.BillingControllerBase;
import br.com.lanchonete.hub.base.exception.APIException;
import br.com.lanchonete.hub.itau.dto.ItauRequestDTO;
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

@Tag(name = "Endpoint Billing Provider Itaú")
@Validated
@RestController
@RequestMapping(path = BillingControllerBase.BASE_PATH+ItauController.BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ItauController extends BillingControllerBase {

    public static final String BASE_PATH = "/itau";

    @Autowired
    private LogRepository logRepository;

    @Counted(value = "execution.count.updateBillingByItau")
    @Timed(value = "execution.time.updateBillingByItau", longTask = true)
    @PutMapping
    public void updateBillingByItau(@RequestBody @Valid ItauRequestDTO itauRequestDTO) throws APIException {
        logRepository.info(ItauController.class, LogCode.LogCodeInfo._0034);

        updateBillingBy(itauRequestDTO.getStatusPaymentType(), itauRequestDTO.getId());
    }
}
