package br.com.lanchonete.usecase.category;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.category.FindAllCategory;

import java.util.List;

public class FindAllCategoryUsecase implements FindAllCategory {

    private final LogRepository logRepository;
    private final CategoryRepository categoryRepository;

    public FindAllCategoryUsecase(CategoryRepository categoryRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        logRepository.info(FindAllCategoryUsecase.class, LogCode.LogCodeInfo._0012);
        List<Category> categories = categoryRepository.findAll();
        logRepository.info(FindAllCategoryUsecase.class, LogCode.LogCodeInfo._0013);
        return categories;
    }
}
