package br.com.lanchonete.rest.output;

import br.com.lanchonete.model.BillingFormType;
import br.com.lanchonete.model.StatusPaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MyBilling {

    private BigDecimal totalPrice;
    private StatusPaymentType status;
    private BillingFormType billingFormType;

}
