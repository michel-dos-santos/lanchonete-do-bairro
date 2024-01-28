package br.com.lanchonete.hub.mercadopago.dto;

import br.com.lanchonete.hub.base.dto.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoRequestDTO extends RequestDTO {

    private UUID id;
    private BigDecimal totalPrice;

}
