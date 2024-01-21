package br.com.lanchonete.rest.mappers.outputs;

import br.com.lanchonete.model.Client;
import br.com.lanchonete.rest.mappers.outputs.dtos.ClientOutputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientOutputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ClientOutputDTO mapClientOutputDTOFromClient(Client client) {
        return modelMapper.map(client, ClientOutputDTO.class);
    }

}
