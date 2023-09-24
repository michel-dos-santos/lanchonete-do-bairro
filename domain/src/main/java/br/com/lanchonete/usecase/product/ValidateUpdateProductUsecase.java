package br.com.lanchonete.usecase.product;

import br.com.lanchonete.exception.category.CategoryNotFoundException;
import br.com.lanchonete.exception.product.ProductFoundException;
import br.com.lanchonete.exception.product.ProductInvalidException;
import br.com.lanchonete.exception.product.ProductNotFoundException;
import br.com.lanchonete.exception.product.ProductNotInformedException;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.ValidateUpdateProduct;

import java.math.BigDecimal;
import java.util.Objects;

public class ValidateUpdateProductUsecase implements ValidateUpdateProduct {

    private final LogRepository logRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ValidateUpdateProductUsecase(LogRepository logRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.logRepository = logRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void validate(Product product) {
        logRepository.info(ValidateUpdateProductUsecase.class, LogCode.LogCodeInfo._0008);

        if (Objects.isNull(product)) {
            throw new ProductNotInformedException();
        }
        if (!Objects.isNull(product.getName()) && product.getName().trim().isEmpty()) {
            throw new ProductInvalidException("name", product.getName());
        }
        if (!Objects.isNull(product.getDescription()) && product.getDescription().trim().isEmpty()) {
            throw new ProductInvalidException("description", product.getDescription());
        }
        if (!Objects.isNull(product.getImage()) && product.getImage().trim().isEmpty()) {
            throw new ProductInvalidException("image", product.getImage());
        }
        if (!Objects.isNull(product.getUnitPrice()) && product.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductInvalidException("unitPrice", ""+product.getUnitPrice());
        }
        if (!Objects.isNull(product.getCategory()) && !Objects.isNull(product.getCategory().getName())) {
            if (product.getCategory().getName().trim().isEmpty()) {
                throw new ProductInvalidException("category.name", product.getCategory().getName());
            }
            if (!categoryRepository.existsByName(product.getCategory().getName())) {
                throw new CategoryNotFoundException("category.name", product.getCategory().getName());
            }

            Product productFound = null;
            try {
                productFound = productRepository.findByNameAndCategoryName(product.getName(), product.getCategory().getName());
            } catch (ProductNotFoundException e) {}

            if (!Objects.isNull(productFound) && !productFound.getId().equals(product.getId())) {
                throw new ProductFoundException("name", "category", product.getName(), product.getCategory().getName());
            }
        }
    }
}
