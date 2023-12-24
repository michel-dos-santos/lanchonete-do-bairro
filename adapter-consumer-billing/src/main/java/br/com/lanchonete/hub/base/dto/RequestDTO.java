package br.com.lanchonete.hub.base.dto;

import br.com.lanchonete.hub.base.enumerate.StatusPaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private StatusPaymentType statusPaymentType;

}
