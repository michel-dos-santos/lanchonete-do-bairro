package br.com.lanchonete.rest.mappers.inputs.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderInputDTO {

    private UUID clientID;
    @NotNull(message = "Cobrança do pedido não pode ser nulo")
    private @Valid BillingInputDTO billing;
    @NotEmpty(message = "Itens do pedido não pode ser vazio ou nulo")
    private List<@Valid OrderItemInputDTO> orderItems;

}
