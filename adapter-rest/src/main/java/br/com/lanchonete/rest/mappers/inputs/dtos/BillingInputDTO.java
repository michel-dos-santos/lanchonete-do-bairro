package br.com.lanchonete.rest.mappers.inputs.dtos;

import br.com.lanchonete.model.BillingFormType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BillingInputDTO {

    @NotNull(message = "Valor total do pedido não pode ser nulo")
    @Digits(integer = 14, fraction = 2)
    @DecimalMin(value = "0", inclusive = false, message = "Valor total do pedido deve ser maior do que zero")
    private BigDecimal totalPrice;
    @NotNull(message = "Forma da cobrança do pedido não pode ser nulo")
    private BillingFormType billingFormType;

}
