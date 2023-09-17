package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Client;

public interface ClientRepository {

    Client save(Client client);
    boolean existsByCPF(String cpf);
    Client identifierByCPF(String cpf);

}
