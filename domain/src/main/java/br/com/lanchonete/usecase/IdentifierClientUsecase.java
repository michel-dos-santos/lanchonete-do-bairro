package br.com.lanchonete.usecase;

import br.com.lanchonete.exception.ClientNotFoundException;
import br.com.lanchonete.model.Client;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.ClientRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.client.IdentifierClient;
import br.com.lanchonete.port.usecase.client.SaveClient;
import br.com.lanchonete.port.usecase.client.ValidateClient;

import java.util.Objects;

public class IdentifierClientUsecase implements IdentifierClient {

    private final LogRepository logRepository;
    private final ClientRepository clientRepository;

    public IdentifierClientUsecase(ClientRepository clientRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Client identifierByCPF(String cpf) {
        logRepository.info(IdentifierClientUsecase.class, LogCode.LogCodeInfo._0004);
        Client client = clientRepository.identifierByCPF(cpf);
        logRepository.info(IdentifierClientUsecase.class, LogCode.LogCodeInfo._0005);
        return client;
    }
}
