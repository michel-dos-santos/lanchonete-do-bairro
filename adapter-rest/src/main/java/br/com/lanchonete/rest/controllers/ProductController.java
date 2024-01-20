package br.com.lanchonete.rest.controllers;

import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.gateways.ProductGateway;
import br.com.lanchonete.rest.gateways.input.ProductInputDTO;
import br.com.lanchonete.rest.gateways.input.ProductPatchInputDTO;
import br.com.lanchonete.rest.presenters.ProductPresenter;
import br.com.lanchonete.rest.presenters.output.ProductOutputDTO;
import br.com.lanchonete.usecase.product.*;
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
import java.util.UUID;

import static br.com.lanchonete.rest.controllers.ProductController.BASE_PATH;

@Tag(name = "Endpoint Products")
@Validated
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    public static final String BASE_PATH = "/v1/products";
    @Autowired
    private ProductGateway productGateway;
    @Autowired
    private ProductPresenter productPresenter;
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
            Product product = productGateway.mapProductFromProductInputDTO(productInputDTO);
            return productPresenter.mapProductOutputDTOFromOrder(saveProductUsecase.save(product));
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
            updateProductUsecase.update(productGateway.mapProductFromProductInputDTO(productInputDTO, id));
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
            updateProductFieldsUsecase.update(productGateway.mapProductFromProductPatchInputDTO(productPatchInputDTO, id));
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
            return productPresenter.mapListProductOutputDTOFromListProduct(findProductsByCategoryIDUsecase.findByCategoryID(categoryID));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

}
