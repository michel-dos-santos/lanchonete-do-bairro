package br.com.lanchonete.rest.mappers.outputs.dtos;

import br.com.lanchonete.model.StatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class MyOrderOutputDTO {

    private UUID id;
    private String number;
    private StatusType status;
    private MyBilling billing;
    private List<MyOrderItems> orderItems;

}