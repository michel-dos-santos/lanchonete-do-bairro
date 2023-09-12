package br.com.lanchonete.usecase;

import br.com.lanchonete.port.repository.ClientRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.SaveClient;

public class SaveClientUsecase implements SaveClient {

    private final LogRepository logRepository;
    private final ClientRepository clientRepository;

    public SaveClientUsecase(ClientRepository clientRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void save() {
        logRepository.info(SaveClientUsecase.class, "Iniciando o processo de criação do cliente no sistema");
    }
}
