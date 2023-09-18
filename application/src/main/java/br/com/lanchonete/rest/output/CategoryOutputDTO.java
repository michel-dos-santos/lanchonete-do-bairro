package br.com.lanchonete.rest.output;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryOutputDTO {

    private String name;
    private String description;

}
