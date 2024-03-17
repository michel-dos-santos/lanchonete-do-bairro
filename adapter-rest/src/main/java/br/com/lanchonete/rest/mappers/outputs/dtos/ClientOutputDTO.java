package br.com.lanchonete.rest.mappers.outputs.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientOutputDTO {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String token;

}
