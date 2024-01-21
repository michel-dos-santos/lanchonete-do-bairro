package br.com.lanchonete.rest.mappers.inputs.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryInputDTO {

    @NotBlank(message = "Nome da categoria não pode ser vazio ou nulo")
    @Size(min = 3, max = 50, message = "Nome da categoria deve ter no mínimo {min} e no máximo {max} caracteres")
    private String name;

    @NotBlank(message = "Descrição da categoria não pode ser vazio ou nulo")
    @Size(min = 3, max = 255, message = "Descrição da categoria deve ter no mínimo {min} e no máximo {max} caracteres")
    private String description;

}
