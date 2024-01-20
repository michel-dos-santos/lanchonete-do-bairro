package br.com.lanchonete.rest.gateways;

import br.com.lanchonete.model.Client;
import br.com.lanchonete.rest.gateways.input.ClientInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientGateway {

    @Autowired
    private ModelMapper modelMapper;

    public Client mapClientFromClientInputDTO(ClientInputDTO clientInputDTO) {
        return modelMapper.map(clientInputDTO, Client.class);
    }

}
