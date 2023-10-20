package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {

    Product save(Product product);
    Product update(Product product);
    boolean existsByNameAndCategoryName(String name, String categoryName);
    Product findById(UUID id);
    Product findByNameAndCategoryName(String name, String categoryName);
    void deleteById(UUID id);
    List<Product> findByCategoryID(UUID categoryID);

}
