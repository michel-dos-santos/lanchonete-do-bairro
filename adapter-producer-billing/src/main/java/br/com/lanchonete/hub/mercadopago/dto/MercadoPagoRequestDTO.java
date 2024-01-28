package br.com.lanchonete.hub.mercadopago.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoRequestDTO {

    private String id;
    private BigDecimal totalPrice;

}
