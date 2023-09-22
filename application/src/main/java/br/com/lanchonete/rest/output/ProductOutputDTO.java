package br.com.lanchonete.rest.output;

import br.com.lanchonete.model.StatusActiveType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductOutputDTO {

    private UUID id;
    private String name;
    private String description;
    private String image;
    private BigDecimal unitPrice;
    private CategoryOutputDTO category;
    private StatusActiveType status;

}
