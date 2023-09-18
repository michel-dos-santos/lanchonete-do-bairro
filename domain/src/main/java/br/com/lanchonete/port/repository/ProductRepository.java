package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Product;

public interface ProductRepository {

    Product save(Product product);

    boolean existsByNameAndCategoryName(String name, String categoryName);

}
