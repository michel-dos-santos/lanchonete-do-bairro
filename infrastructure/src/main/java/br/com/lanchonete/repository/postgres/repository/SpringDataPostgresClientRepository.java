package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.repository.postgres.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPostgresClientRepository extends JpaRepository<ClientEntity, UUID> {
    boolean existsByCpf(String cpf);
    Optional<ClientEntity> findByCpf(String cpf);

}
