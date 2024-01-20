package br.com.lanchonete.rest.presenters;

import br.com.lanchonete.model.Product;
import br.com.lanchonete.rest.presenters.output.ProductOutputDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPresenter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductOutputDTO mapProductOutputDTOFromOrder(Product product) {
        return modelMapper.map(product, ProductOutputDTO.class);
    }

    public List<ProductOutputDTO> mapListProductOutputDTOFromListProduct(List<Product> products) {
        return modelMapper.map(products, new TypeToken<List<ProductOutputDTO>>() {}.getType());
    }

}
