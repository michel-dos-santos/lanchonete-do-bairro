package br.com.lanchonete.rest.mappers.inputs;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.rest.mappers.inputs.dtos.CategoryInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryInputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Category mapCategoryFromCategoryInputDTO(CategoryInputDTO categoryInputDTO) {
        return modelMapper.map(categoryInputDTO, Category.class);
    }

}
