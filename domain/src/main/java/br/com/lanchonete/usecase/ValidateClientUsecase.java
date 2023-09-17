package br.com.lanchonete.usecase;

import br.com.lanchonete.exception.ClientInvalidException;
import br.com.lanchonete.exception.ClientNotInformedException;
import br.com.lanchonete.model.Client;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.ClientRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.client.ValidateClient;
import br.com.lanchonete.utils.validators.CpfValidator;

import java.util.Objects;

public class ValidateClientUsecase implements ValidateClient {

    private final LogRepository logRepository;

    public ValidateClientUsecase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void validate(Client client) {
        logRepository.info(ValidateClientUsecase.class, LogCode.LogCodeInfo._0002);

        if (Objects.isNull(client)) {
            throw new ClientNotInformedException();
        }

        if (!CpfValidator.isValid(client.getCpf())) {
            throw new ClientInvalidException("cpf", client.getCpf());
        }

        if (Objects.isNull(client.getName()) || client.getName().trim().isEmpty()) {
            throw new ClientInvalidException("name", client.getName());
        }

        if (Objects.isNull(client.getEmail()) || client.getEmail().trim().isEmpty()) {
            throw new ClientInvalidException("email", client.getEmail());
        }
    }
}
