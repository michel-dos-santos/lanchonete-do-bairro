package br.com.lanchonete.rest.gateways;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.rest.gateways.input.ProductInputDTO;
import br.com.lanchonete.rest.gateways.input.ProductPatchInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductGateway {

    @Autowired
    private ModelMapper modelMapper;

    public Product mapProductFromProductInputDTO(ProductInputDTO productInputDTO) {
        Category category = new Category();
        category.setName(productInputDTO.getCategoryName());
        Product product = modelMapper.map(productInputDTO, Product.class);
        product.setCategory(category);

        return product;
    }

    public Product mapProductFromProductInputDTO(ProductInputDTO productInputDTO, UUID id) {
        Category category = new Category();
        category.setName(productInputDTO.getCategoryName());
        Product product = modelMapper.map(productInputDTO, Product.class);
        product.setCategory(category);
        product.setId(id);

        return product;
    }

    public Product mapProductFromProductPatchInputDTO(ProductPatchInputDTO productPatchInputDTO, UUID id) {
        Category category = new Category();
        category.setName(productPatchInputDTO.getCategoryName());
        Product product = modelMapper.map(productPatchInputDTO, Product.class);
        product.setCategory(category);
        product.setId(id);

        return product;
    }

}
