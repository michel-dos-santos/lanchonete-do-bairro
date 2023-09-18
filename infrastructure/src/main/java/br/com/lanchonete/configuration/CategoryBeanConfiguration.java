package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.category.ValidateCategory;
import br.com.lanchonete.usecase.category.FindAllCategoryUsecase;
import br.com.lanchonete.usecase.category.SaveCategoryUsecase;
import br.com.lanchonete.usecase.category.ValidateCategoryUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryBeanConfiguration {

    @Bean
    SaveCategoryUsecase saveCategory(CategoryRepository productRepository, LogRepository logRepository, ValidateCategory validateCategory) {
        return new SaveCategoryUsecase(productRepository, logRepository, validateCategory);
    }
    @Bean
    ValidateCategoryUsecase validateCategory(LogRepository logRepository) {
        return new ValidateCategoryUsecase(logRepository);
    }

    @Bean
    FindAllCategoryUsecase findAllCategory(CategoryRepository categoryRepository, LogRepository logRepository) {
        return new FindAllCategoryUsecase(categoryRepository, logRepository);
    }

}
