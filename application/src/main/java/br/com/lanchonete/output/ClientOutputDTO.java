package br.com.lanchonete.output;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientOutputDTO {

    private String name;
    private String cpf;
    private String email;

}
