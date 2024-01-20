package br.com.lanchonete.rest.controllers;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.gateways.CategoryGateway;
import br.com.lanchonete.rest.gateways.input.CategoryInputDTO;
import br.com.lanchonete.rest.presenters.CategoryPresenter;
import br.com.lanchonete.rest.presenters.output.CategoryOutputDTO;
import br.com.lanchonete.usecase.category.FindAllCategoryUsecase;
import br.com.lanchonete.usecase.category.SaveCategoryUsecase;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static br.com.lanchonete.rest.controllers.CategoryController.BASE_PATH;

@Tag(name = "Endpoint Categories")
@Validated
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    public static final String BASE_PATH = "/v1/categories";
    @Autowired
    private CategoryGateway categoryGateway;
    @Autowired
    private CategoryPresenter categoryPresenter;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private SaveCategoryUsecase saveCategoryUsecase;
    @Autowired
    private FindAllCategoryUsecase findAllCategoryUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a criação da categoria foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do categoria")
    @Counted(value = "execution.count.saveCategory")
    @Timed(value = "execution.time.saveCategory", longTask = true)
    @PostMapping
    public CategoryOutputDTO saveCategory(@RequestBody @Valid CategoryInputDTO categoryInputDTO) throws APIException {
        try {
            Category category = categoryGateway.mapCategoryFromCategoryInputDTO(categoryInputDTO);
            return categoryPresenter.mapCategoryOutputDTOFromCategory(saveCategoryUsecase.save(category));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a busca das categorias foi executada com sucesso") })
    @Operation(summary = "Lista todas as categorias")
    @Counted(value = "execution.count.findAllCategories")
    @Timed(value = "execution.time.findAllCategories", longTask = true)
    @GetMapping
    public List<CategoryOutputDTO> findAllCategories() throws APIException {
        try {
            return categoryPresenter.mapListCategoryOutputDTOFromListCategory(findAllCategoryUsecase.findAll());
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

}
