package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.repository.postgres.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface SpringDataPostgresProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = "select case when count(p)> 0 then true else false end from ProductEntity p inner join p.category c where lower(c.name) = :categoryName and p.name = :name")
    boolean existsByNameAndCategory(String name, String categoryName);

}
