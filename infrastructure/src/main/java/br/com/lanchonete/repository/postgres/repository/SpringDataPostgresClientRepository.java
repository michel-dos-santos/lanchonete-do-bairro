package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.repository.postgres.entity.ClientEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface SpringDataPostgresClientRepository extends PagingAndSortingRepository<ClientEntity, UUID> {

}
