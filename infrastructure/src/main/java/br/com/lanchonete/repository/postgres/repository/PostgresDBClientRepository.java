package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.entity.Client;
import br.com.lanchonete.port.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

        return null;
    }
}
