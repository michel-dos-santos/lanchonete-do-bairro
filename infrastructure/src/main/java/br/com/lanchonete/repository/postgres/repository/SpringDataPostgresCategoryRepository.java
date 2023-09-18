package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.repository.postgres.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPostgresCategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    boolean existsByName(String name);

    Optional<CategoryEntity> findByName(String name);

}
