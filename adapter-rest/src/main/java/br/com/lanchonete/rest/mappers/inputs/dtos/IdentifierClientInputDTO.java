package br.com.lanchonete.rest.mappers.inputs.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
public class IdentifierClientInputDTO {

    @NotBlank(message = "CPF não pode ser vazio ou nulo")
    @CPF(message = "CPF inválido")
    private String username;

    @NotBlank(message = "Senha não pode ser vazio ou nulo")
    @Size(min = 8, max = 99, message = "Senha deve ter no mínimo {min} e no máximo {max} caracteres")
    private String password;

}
