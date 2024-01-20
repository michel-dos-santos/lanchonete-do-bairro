package br.com.lanchonete.rest.presenters.output;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyOrderItems {

    private String observation;
    private Integer quantity;
    private MyProductOrderItem product;

}
