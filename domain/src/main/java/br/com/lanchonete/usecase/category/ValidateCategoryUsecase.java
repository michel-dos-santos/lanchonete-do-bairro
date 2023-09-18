package br.com.lanchonete.usecase.category;

import br.com.lanchonete.exception.CategoryInvalidException;
import br.com.lanchonete.exception.CategoryNotInformedException;
import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.category.ValidateCategory;

import java.util.Objects;

public class ValidateCategoryUsecase implements ValidateCategory {

    private final LogRepository logRepository;
    public ValidateCategoryUsecase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void validate(Category category) {
        logRepository.info(ValidateCategoryUsecase.class, LogCode.LogCodeInfo._0011);

        if (Objects.isNull(category)) {
            throw new CategoryNotInformedException();
        }
        if (Objects.isNull(category.getName()) || category.getName().trim().isEmpty()) {
            throw new CategoryInvalidException("name", category.getName());
        }
        if (Objects.isNull(category.getDescription()) || category.getDescription().trim().isEmpty()) {
            throw new CategoryInvalidException("description", category.getDescription());
        }
    }
}
