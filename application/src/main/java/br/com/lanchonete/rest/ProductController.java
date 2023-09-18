package br.com.lanchonete.rest;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.input.ProductInputDTO;
import br.com.lanchonete.usecase.product.SaveProductUsecase;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static br.com.lanchonete.rest.ProductController.BASE_PATH;

@Tag(name = "Endpoint Products")
@Validated
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    public static final String BASE_PATH = "/v1/products";
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private SaveProductUsecase saveProductUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a criação do produto foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do produto e associa a categoria")
    @Counted(value = "execution.count.saveProduct")
    @Timed(value = "execution.time.saveProduct", longTask = true)
    @PostMapping
    public void saveProduct(@RequestBody @Valid ProductInputDTO productInputDTO) throws APIException {
        try {
            Category category = new Category();
            category.setName(productInputDTO.getCategoryName());
            Product product = modelMapper.map(productInputDTO, Product.class);
            product.setCategory(category);

            saveProductUsecase.save(product);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }


}
