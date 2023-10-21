package br.com.lanchonete.postgres.repository;

import br.com.lanchonete.exception.client.ClientFoundException;
import br.com.lanchonete.exception.client.ClientNotFoundException;
import br.com.lanchonete.model.Client;
import br.com.lanchonete.port.repository.ClientRepository;
import br.com.lanchonete.postgres.entity.ClientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class PostgresDBClientRepository implements ClientRepository {

    private final SpringDataPostgresClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public PostgresDBClientRepository(SpringDataPostgresClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Client save(Client client) {
        if (existsByCPF(client.getCpf())) {
            throw new ClientFoundException("cpf", client.getCpf());
        }

        ClientEntity clientEntity = modelMapper.map(client, ClientEntity.class);
        clientRepository.save(clientEntity);
        return modelMapper.map(clientEntity, Client.class);
    }

    @Override
    @Transactional
    public boolean existsByCPF(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }

    @Override
    public Client identifierByCPF(String cpf) {
        Optional<ClientEntity> clientEntityOptional = clientRepository.findByCpf(cpf);
        if (clientEntityOptional.isEmpty()) {
            throw new ClientNotFoundException("cpf", cpf);
        }

        return modelMapper.map(clientEntityOptional.get(), Client.class);
    }

}
