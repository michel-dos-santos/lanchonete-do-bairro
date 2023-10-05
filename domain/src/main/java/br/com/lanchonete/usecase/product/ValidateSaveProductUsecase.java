package br.com.lanchonete.usecase.product;

import br.com.lanchonete.exception.category.CategoryNotFoundException;
import br.com.lanchonete.exception.product.ProductFoundException;
import br.com.lanchonete.exception.product.ProductInvalidException;
import br.com.lanchonete.exception.product.ProductNotInformedException;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.ValidateSaveProduct;

import java.math.BigDecimal;
import java.util.Objects;

public class ValidateSaveProductUsecase implements ValidateSaveProduct {

    private final LogRepository logRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ValidateSaveProductUsecase(LogRepository logRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.logRepository = logRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void validate(Product product) {
        logRepository.info(ValidateSaveProductUsecase.class, LogCode.LogCodeInfo._0008);

        if (Objects.isNull(product)) {
            throw new ProductNotInformedException();
        }
        if (Objects.isNull(product.getName()) || product.getName().trim().isEmpty()) {
            throw new ProductInvalidException("name", product.getName());
        }
        if (Objects.isNull(product.getDescription()) || product.getDescription().trim().isEmpty()) {
            throw new ProductInvalidException("description", product.getDescription());
        }
        if (Objects.isNull(product.getImage()) || product.getImage().trim().isEmpty()) {
            throw new ProductInvalidException("image", product.getImage());
        }
        if (Objects.isNull(product.getUnitPrice()) || product.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductInvalidException("unitPrice", ""+product.getUnitPrice());
        }
        if (Objects.isNull(product.getCategory())) {
            throw new ProductInvalidException("category", null);
        }
        if (Objects.isNull(product.getCategory().getName())) {
            throw new ProductInvalidException("category.name", product.getCategory().getName());
        }
        if (!categoryRepository.existsByName(product.getCategory().getName())) {
            throw new CategoryNotFoundException("category.name", product.getCategory().getName());
        }
        if (productRepository.existsByNameAndCategoryName(product.getName(), product.getCategory().getName())) {
            throw new ProductFoundException("name", "category", product.getName(), product.getCategory().getName());
        }
    }
}
