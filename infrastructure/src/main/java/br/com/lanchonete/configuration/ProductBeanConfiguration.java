package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.ValidateSaveProduct;
import br.com.lanchonete.port.usecase.product.ValidateUpdateProduct;
import br.com.lanchonete.usecase.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeanConfiguration {

    @Bean
    SaveProductUsecase saveProduct(ProductRepository productRepository, LogRepository logRepository, ValidateSaveProduct validateProduct) {
        return new SaveProductUsecase(productRepository, logRepository, validateProduct);
    }
    @Bean
    ValidateSaveProductUsecase validateSaveProduct(LogRepository logRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        return new ValidateSaveProductUsecase(logRepository, categoryRepository, productRepository);
    }
    @Bean
    UpdateProductUsecase updateProduct(ProductRepository productRepository, LogRepository logRepository, ValidateUpdateProduct validateProduct) {
        return new UpdateProductUsecase(productRepository, logRepository, validateProduct);
    }
    @Bean
    ValidateUpdateProductUsecase validateUpdateProduct(LogRepository logRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        return new ValidateUpdateProductUsecase(logRepository, categoryRepository, productRepository);
    }

    @Bean
    DeleteProductUsecase deleteProduct(LogRepository logRepository, ProductRepository productRepository) {
        return new DeleteProductUsecase(productRepository, logRepository);
    }

    @Bean
    UpdateProductFieldsUsecase updateProductFields(LogRepository logRepository, ProductRepository productRepository, ValidateUpdateProduct validateProduct) {
        return new UpdateProductFieldsUsecase(productRepository, logRepository, validateProduct);
    }

    @Bean
    FindProductsByCategoryIDUsecase findProductsByCategoryID(LogRepository logRepository, ProductRepository productRepository) {
        return new FindProductsByCategoryIDUsecase(productRepository, logRepository);
    }

}
