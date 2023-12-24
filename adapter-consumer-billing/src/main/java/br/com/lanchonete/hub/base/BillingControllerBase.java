package br.com.lanchonete.hub.base;

import br.com.lanchonete.hub.base.enumerate.StatusPaymentType;
import br.com.lanchonete.hub.base.exception.APIException;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.usecase.billing.UpdateBillingByHubUsecase;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.UUID;

@Tag(name = "Endpoint Billing Provider")
@Validated
public abstract class BillingControllerBase {

    public static final String BASE_PATH = "/v1/consumer";
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private UpdateBillingByHubUsecase updateBillingByHubUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizada informações sobre o pagamento do pedido com sucesso") })
    @Operation(summary = "Persiste os dados do pagamento do pedido")
    @Counted(value = "execution.count.updateBillingBy")
    @Timed(value = "execution.time.updateBillingBy", longTask = true)
    public void updateBillingBy(@RequestBody @Valid StatusPaymentType statusPaymentType, UUID id) throws APIException {
        try {
            br.com.lanchonete.model.StatusPaymentType status = br.com.lanchonete.model.StatusPaymentType.get(statusPaymentType.name());
            updateBillingByHubUsecase.updateStatusPaymentType(status, id);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }
}
