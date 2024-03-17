package br.com.lanchonete.usecase.category;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllCategoryUsecaseImplTests {

    @InjectMocks
    private FindAllCategoryUsecase findAllByOriginUseCase;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private LogRepository logRepository;
    private static EasyRandom easyRandom;

    @BeforeAll
    public static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @Test
    public void findAll() throws Exception {
        List<Category> categories = easyRandom.objects(Category.class, 10).toList();
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> allCategory = categoryRepository.findAll();

        assertThat(allCategory).isNotNull();
        assertThat(allCategory.size()).isEqualTo(categories.size());
        assertThat(allCategory).containsExactlyElementsOf(categories);

    }

}
