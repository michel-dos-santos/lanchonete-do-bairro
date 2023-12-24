package br.com.lanchonete.rest.output;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MyProductOrderItem {

    private String name;
    private BigDecimal unitPrice;

}
