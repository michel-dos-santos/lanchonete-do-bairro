package br.com.lanchonete.rest.input;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductInputDTO {

    private transient UUID id;

    @NotBlank(message = "Nome do produto não pode ser vazio ou nulo")
    @Size(min = 3, max = 50, message = "Nome do produto deve ter no mínimo {min} e no máximo {max} caracteres")
    private String name;

    @NotBlank(message = "Descrição do produto não pode ser vazio ou nulo")
    @Size(min = 3, max = 255, message = "Descrição do produto deve ter no mínimo {min} e no máximo {max} caracteres")
    private String description;

    @NotBlank(message = "URL da imagem do produto não pode ser vazio ou nulo")
    @Size(max = 1024, message = "URL da imagem do produto deve ter no máximo {max} caracteres")
    private String image;

    @Digits(integer = 14, fraction = 2)
    @DecimalMin(value = "0", inclusive = false, message = "Valor unitário do produto deve ser maior do que zero")
    private BigDecimal unitPrice;

    @NotBlank(message = "Categoria do produto não pode ser vazio ou nulo")
    private String categoryName;

}
