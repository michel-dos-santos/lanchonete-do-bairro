package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    boolean existsByName(String name);

    List<Category> findAll();

    Optional<Category> findByName(String name);

}
