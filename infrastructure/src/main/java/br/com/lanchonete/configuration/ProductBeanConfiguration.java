package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.ValidateProduct;
import br.com.lanchonete.usecase.product.SaveProductUsecase;
import br.com.lanchonete.usecase.product.ValidateProductUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeanConfiguration {

    @Bean
    SaveProductUsecase saveProduct(ProductRepository productRepository, LogRepository logRepository, ValidateProduct validateProduct) {
        return new SaveProductUsecase(productRepository, logRepository, validateProduct);
    }
    @Bean
    ValidateProductUsecase validateProduct(LogRepository logRepository, CategoryRepository categoryRepository) {
        return new ValidateProductUsecase(logRepository, categoryRepository);
    }

}
