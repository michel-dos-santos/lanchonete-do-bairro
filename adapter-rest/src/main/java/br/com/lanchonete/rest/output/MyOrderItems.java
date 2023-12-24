package br.com.lanchonete.rest.output;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyOrderItems {

    private String observation;
    private Integer quantity;
    private MyProductOrderItem product;

}
