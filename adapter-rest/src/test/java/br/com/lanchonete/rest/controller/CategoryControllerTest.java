package br.com.lanchonete.rest.controller;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.port.usecase.category.FindAllCategory;
import br.com.lanchonete.port.usecase.category.SaveCategory;
import br.com.lanchonete.rest.controllers.CategoryController;
import br.com.lanchonete.rest.mappers.inputs.CategoryInputMapper;
import br.com.lanchonete.rest.mappers.inputs.dtos.CategoryInputDTO;
import br.com.lanchonete.rest.mappers.outputs.CategoryOutputMapper;
import br.com.lanchonete.rest.mappers.outputs.dtos.CategoryOutputDTO;
import br.com.lanchonete.usecase.category.FindAllCategoryUsecase;
import br.com.lanchonete.usecase.category.SaveCategoryUsecase;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest extends ControllerTestBase {

    @MockBean
    private CategoryInputMapper categoryInputMapper;
    @MockBean
    private CategoryOutputMapper categoryOutputMapper;
    @MockBean
    private SaveCategoryUsecase saveCategoryUsecase;
    @MockBean
    private FindAllCategoryUsecase findAllCategoryUsecase;

    @Test
    public void saveCategorySuccess() throws Exception {
        String url = CategoryController.BASE_PATH;
        CategoryInputDTO categoryInputDTO = easyRandom.nextObject(CategoryInputDTO.class);
        Category category = modelMapperAPI.map(categoryInputDTO, Category.class);
        category.setId(easyRandom.nextObject(UUID.class));
        CategoryOutputDTO categoryOutputDTO = modelMapperAPI.map(category, CategoryOutputDTO.class);

        when(categoryInputMapper.mapCategoryFromCategoryInputDTO(categoryInputDTO)).thenReturn(category);
        when(saveCategoryUsecase.save(any())).thenReturn(category);
        when(categoryOutputMapper.mapCategoryOutputDTOFromCategory(category)).thenReturn(categoryOutputDTO);

        categoryOutputDTO =
                doPost(url, categoryInputDTO, HttpStatus.OK, new TypeReference<CategoryOutputDTO>() {});

        assertThat(!Objects.isNull(categoryOutputDTO.getId()));
        assertThat(categoryOutputDTO.getName().equals(categoryInputDTO.getName()));
        assertThat(categoryOutputDTO.getDescription().equals(categoryInputDTO.getDescription()));
    }

    @Test
    public void saveCategoryException() throws Exception {
        String url = CategoryController.BASE_PATH;
        CategoryInputDTO categoryInputDTO = easyRandom.nextObject(CategoryInputDTO.class);
        Category category = modelMapperAPI.map(categoryInputDTO, Category.class);
        category.setId(easyRandom.nextObject(UUID.class));

        when(categoryInputMapper.mapCategoryFromCategoryInputDTO(categoryInputDTO)).thenReturn(category);
        when(saveCategoryUsecase.save(any())).thenThrow(new MockitoException("Categoria não informado"));

        ResultActions resultActions = doPost(url, categoryInputDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    public void findAllCategoriesSuccess() throws Exception {
        String url = CategoryController.BASE_PATH;
        List<Category> categories = new ArrayList<>(easyRandom.objects(Category.class, 10).toList());
        List<CategoryOutputDTO> categoriesOutput = List.of(modelMapperAPI.map(categories, CategoryOutputDTO[].class));

        when(findAllCategoryUsecase.findAll()).thenReturn(categories);
        when(categoryOutputMapper.mapListCategoryOutputDTOFromListCategory(categories)).thenReturn(categoriesOutput);

        List<CategoryOutputDTO> output = doGet(url, HttpStatus.OK, new TypeReference<>() {});

        assertThatList(output);
        assertThat(output.size() == 10);
    }

}
