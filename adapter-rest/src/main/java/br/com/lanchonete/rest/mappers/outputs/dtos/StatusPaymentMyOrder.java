package br.com.lanchonete.rest.mappers.outputs.dtos;

import br.com.lanchonete.model.StatusPaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusPaymentMyOrder {

    private StatusPaymentType status;

}
