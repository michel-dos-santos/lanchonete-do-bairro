package br.com.lanchonete.rest.gateways;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.rest.gateways.input.CategoryInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryGateway {

    @Autowired
    private ModelMapper modelMapper;

    public Category mapCategoryFromCategoryInputDTO(CategoryInputDTO categoryInputDTO) {
        return modelMapper.map(categoryInputDTO, Category.class);
    }

}
