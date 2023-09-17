package br.com.lanchonete.port.usecase.client;

import br.com.lanchonete.model.Client;

public interface IdentifierClient {

    Client identifierByCPF(String cpf);

}
