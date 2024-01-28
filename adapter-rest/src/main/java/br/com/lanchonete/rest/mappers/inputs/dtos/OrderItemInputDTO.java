package br.com.lanchonete.rest.mappers.inputs.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderItemInputDTO {

    @Size(max = 255, message = "Observação do item do pedido deve ter no máximo {max} caracteres")
    private String observation;
    @NotNull(message = "Identificação do produto não pode ser nulo")
    private UUID productID;
    @NotNull(message = "Quantidade do item do produto não pode ser nulo")
    @Max(value = 9999, message = "Quantidade do item do pedido deve ter no máximo {value} caracteres")
    private Integer quantity;

}
