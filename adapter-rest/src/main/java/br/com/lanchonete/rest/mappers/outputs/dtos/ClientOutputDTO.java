package br.com.lanchonete.rest.mappers.outputs.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ClientOutputDTO {

    private UUID id;
    private String name;
    private String cpf;
    private String email;

}
