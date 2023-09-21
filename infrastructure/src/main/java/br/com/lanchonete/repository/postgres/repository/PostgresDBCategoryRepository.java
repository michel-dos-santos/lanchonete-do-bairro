package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.exception.CategoryFoundException;
import br.com.lanchonete.model.Category;
import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.repository.postgres.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class PostgresDBCategoryRepository implements CategoryRepository {

    private final SpringDataPostgresCategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public PostgresDBCategoryRepository(SpringDataPostgresCategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Category save(Category category) {
        if (existsByName(category.getName())) {
            throw new CategoryFoundException("name", category.getName());
        }

        CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);
        return modelMapper.map(categoryRepository.save(categoryEntity), Category.class);
    }

    @Override
    @Transactional
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        Type type = new TypeToken<List<Category>>() {}.getType();
        return modelMapper.map(categoryEntityList, type);
    }

    @Override
    public Optional<Category> findByName(String name) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findByName(name);
        if (categoryEntityOptional.isPresent()) {
            return Optional.of(modelMapper.map(categoryEntityOptional.get(), Category.class));
        }
        return Optional.empty();
    }
}
