package br.com.lanchonete.rest.gateways.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
public class ClientInputDTO {

    @NotBlank(message = "Nome não pode ser vazio ou nulo")
    @Size(min = 3, max = 50, message = "Nome do cliente deve ter no mínimo {min} e no máximo {max} caracteres")
    private String name;

    @NotBlank(message = "CPF não pode ser vazio ou nulo")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Email não pode ser vazio ou nulo")
    @Email(message = "Email inválido")
    private String email;

}
