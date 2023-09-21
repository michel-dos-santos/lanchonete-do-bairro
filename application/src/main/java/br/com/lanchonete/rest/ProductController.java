package br.com.lanchonete.rest;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.input.ProductInputDTO;
import br.com.lanchonete.rest.input.ProductPatchInputDTO;
import br.com.lanchonete.rest.output.ProductOutputDTO;
import br.com.lanchonete.usecase.product.*;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private UpdateProductUsecase updateProductUsecase;
    @Autowired
    private UpdateProductFieldsUsecase updateProductFieldsUsecase;
    @Autowired
    private DeleteProductUsecase deleteProductUsecase;
    @Autowired
    private FindProductsByCategoryIDUsecase findProductsByCategoryIDUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a criação do produto foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do produto e associa a categoria")
    @Counted(value = "execution.count.saveProduct")
    @Timed(value = "execution.time.saveProduct", longTask = true)
    @PostMapping
    public ProductOutputDTO saveProduct(@RequestBody @Valid ProductInputDTO productInputDTO) throws APIException {
        try {
            Category category = new Category();
            category.setName(productInputDTO.getCategoryName());
            Product product = modelMapper.map(productInputDTO, Product.class);
            product.setCategory(category);

            return modelMapper.map(saveProductUsecase.save(product), ProductOutputDTO.class);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a atualização do produto foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do produto")
    @Counted(value = "execution.count.updateProduct")
    @Timed(value = "execution.time.updateProduct", longTask = true)
    @PutMapping(value = "/{id}")
    public void updateProduct(
            @PathVariable UUID id,
            @RequestBody @Valid ProductInputDTO productInputDTO) throws APIException {
        try {
            Category newCategory = new Category();
            newCategory.setName(productInputDTO.getCategoryName());
            Product product = modelMapper.map(productInputDTO, Product.class);
            product.setCategory(newCategory);
            product.setId(id);

            updateProductUsecase.update(product);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a exclusão do produto foi executada com sucesso") })
    @Operation(summary = "Exclui os dados do produto")
    @Counted(value = "execution.count.deleteProduct")
    @Timed(value = "execution.time.deleteProduct", longTask = true)
    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable UUID id) throws APIException {
        try {
            deleteProductUsecase.deleteById(id);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a atualização do produto foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do produto")
    @Counted(value = "execution.count.patchProduct")
    @Timed(value = "execution.time.patchProduct", longTask = true)
    @PatchMapping(value = "/{id}")
    public void patchProduct(
            @PathVariable UUID id,
            @RequestBody ProductPatchInputDTO productPatchInputDTO) throws APIException {
        try {
            Category newCategory = new Category();
            newCategory.setName(productPatchInputDTO.getCategoryName());
            Product product = modelMapper.map(productPatchInputDTO, Product.class);
            product.setCategory(newCategory);
            product.setId(id);

            updateProductFieldsUsecase.update(product);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a busca do produto pelo identificador da categoria foi executada com sucesso") })
    @Operation(summary = "Lista os produtos com base na categoria")
    @Counted(value = "execution.count.getProductsByCategoryID")
    @Timed(value = "execution.time.getProductsByCategoryID", longTask = true)
    @GetMapping(value = "/category-id/{categoryID}")
    public List<ProductOutputDTO> getProductsByCategoryID(@PathVariable UUID categoryID) throws APIException {
        try {
            List<Product> products = findProductsByCategoryIDUsecase.findByCategoryID(categoryID);
            Type type = new TypeToken<List<ProductOutputDTO>>() {}.getType();
            return modelMapper.map(products, type);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

}
