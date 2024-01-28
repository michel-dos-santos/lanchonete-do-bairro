package br.com.lanchonete.hub.itau.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItauRequestDTO {

    private String id;
    private BigDecimal totalPrice;

}
