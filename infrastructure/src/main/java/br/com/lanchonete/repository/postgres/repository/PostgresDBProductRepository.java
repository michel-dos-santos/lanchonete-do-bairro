package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.exception.CategoryFoundException;
import br.com.lanchonete.exception.ProductFoundException;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.repository.postgres.entity.CategoryEntity;
import br.com.lanchonete.repository.postgres.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PostgresDBProductRepository implements ProductRepository {

    private final SpringDataPostgresProductRepository productRepository;
    private final SpringDataPostgresCategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public PostgresDBProductRepository(SpringDataPostgresProductRepository productRepository, SpringDataPostgresCategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        if (existsByNameAndCategoryName(product.getName(), product.getCategory().getName())) {
            throw new ProductFoundException("name", "category", product.getName(), product.getCategory().getName());
        }

        if (!categoryRepository.existsByName(product.getCategory().getName())) {
            throw new CategoryFoundException("name", product.getCategory().getName());
        }

        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        CategoryEntity categoryEntity = categoryRepository.findByName(product.getCategory().getName()).get();
        productEntity.setCategory(categoryEntity);
        productRepository.save(productEntity);
        return product;
    }

    @Override
    @Transactional
    public boolean existsByNameAndCategoryName(String name, String categoryName) {
        return productRepository.existsByNameAndCategory(name, categoryName);
    }
}
