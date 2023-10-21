package br.com.lanchonete.postgres.repository;

import br.com.lanchonete.postgres.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPostgresProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = "select case when count(p)> 0 then true else false end from ProductEntity p inner join p.category c where lower(c.name) = lower(:categoryName) and lower(p.name) = lower(:name)")
    boolean existsByNameAndCategoryName(String name, String categoryName);

    @Query(value = "select p from ProductEntity p inner join p.category c where lower(c.name) = lower(:categoryName) and lower(p.name) = lower(:name)")
    Optional<ProductEntity> findByNameAndCategoryName(String name, String categoryName);

    @Query(value = "select p from ProductEntity p inner join p.category c on c.id = :categoryID")
    List<ProductEntity> findAllByCategoryID(UUID categoryID);

}
