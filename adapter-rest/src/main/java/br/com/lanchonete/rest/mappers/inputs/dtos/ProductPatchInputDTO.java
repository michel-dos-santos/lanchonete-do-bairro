package br.com.lanchonete.rest.mappers.inputs.dtos;

import br.com.lanchonete.model.StatusActiveType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductPatchInputDTO {

    private transient UUID id;

    private String name;
    private String description;
    private String image;
    private BigDecimal unitPrice;
    private String categoryName;
    private StatusActiveType status;

}
