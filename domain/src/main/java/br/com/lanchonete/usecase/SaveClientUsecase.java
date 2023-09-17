package br.com.lanchonete.usecase;

import br.com.lanchonete.model.Client;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.ClientRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.client.SaveClient;
import br.com.lanchonete.port.usecase.client.ValidateClient;

public class SaveClientUsecase implements SaveClient {

    private final LogRepository logRepository;
    private final ClientRepository clientRepository;
    private final ValidateClient validateClient;

    public SaveClientUsecase(ClientRepository clientRepository, LogRepository logRepository, ValidateClient validateClient) {
        this.logRepository = logRepository;
        this.clientRepository = clientRepository;
        this.validateClient = validateClient;
    }

    @Override
    public Client save(Client client) {
        logRepository.info(SaveClientUsecase.class, LogCode.LogCodeInfo._0001);
        validateClient.validate(client);
        Client clientSaved = clientRepository.save(client);
        logRepository.info(SaveClientUsecase.class, LogCode.LogCodeInfo._0003);
        return clientSaved;
    }
}
