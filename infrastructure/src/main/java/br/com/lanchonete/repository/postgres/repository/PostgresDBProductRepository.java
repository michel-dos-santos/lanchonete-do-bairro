package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.exception.ProductNotFoundException;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.repository.postgres.entity.CategoryEntity;
import br.com.lanchonete.repository.postgres.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        CategoryEntity categoryEntity = categoryRepository.findByName(product.getCategory().getName()).get();
        productEntity.setCategory(categoryEntity);
        return modelMapper.map(productRepository.save(productEntity), Product.class);
    }

    @Override
    public Product update(Product product) {
        ProductEntity productEntity = modelMapper.map(findById(product.getId()), ProductEntity.class);

        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setImage(product.getImage());
        productEntity.setUnitPrice(product.getUnitPrice());
        productEntity.setCategory(categoryRepository.findByName(product.getCategory().getName()).get());
        productEntity.setStatus(product.getStatus());

        productRepository.save(productEntity);
        return modelMapper.map(productEntity, Product.class);
    }

    @Override
    @Transactional
    public boolean existsByNameAndCategoryName(String name, String categoryName) {
        return productRepository.existsByNameAndCategoryName(name, categoryName);
    }

    @Override
    @Transactional
    public Product findById(UUID id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isEmpty()) {
            throw new ProductNotFoundException("id", null, id.toString(), null);
        }
        return modelMapper.map(productEntityOptional.get(), Product.class);
    }

    @Override
    public Product findByNameAndCategoryName(String name, String categoryName) {
        Optional<ProductEntity> productEntityOptional = productRepository.findByNameAndCategoryName(name, categoryName);
        if (productEntityOptional.isEmpty()) {
            throw new ProductNotFoundException("name", "categoryName", name, categoryName);
        }
        return modelMapper.map(productEntityOptional.get(), Product.class);
    }

    @Override
    public void deleteById(UUID id) {
        findById(id);
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByCategoryID(UUID categoryID) {
        List<ProductEntity> productEntityList = productRepository.findAllByCategoryID(categoryID);
        Type type = new TypeToken<List<Product>>() {}.getType();
        return modelMapper.map(productEntityList, type);
    }
}
