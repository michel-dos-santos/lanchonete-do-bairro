package br.com.lanchonete.usecase.category;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.category.SaveCategory;
import br.com.lanchonete.port.usecase.category.ValidateCategory;

public class SaveCategoryUsecase implements SaveCategory {

    private final LogRepository logRepository;
    private final CategoryRepository categoryRepository;
    private final ValidateCategory validateCategory;

    public SaveCategoryUsecase(CategoryRepository categoryRepository, LogRepository logRepository, ValidateCategory validateCategory) {
        this.logRepository = logRepository;
        this.categoryRepository = categoryRepository;
        this.validateCategory = validateCategory;
    }

    @Override
    public Category save(Category category) {
        logRepository.info(SaveCategoryUsecase.class, LogCode.LogCodeInfo._0009);
        validateCategory.validate(category);
        Category categorySaved = categoryRepository.save(category);
        logRepository.info(SaveCategoryUsecase.class, LogCode.LogCodeInfo._0010);
        return categorySaved;
    }
}
