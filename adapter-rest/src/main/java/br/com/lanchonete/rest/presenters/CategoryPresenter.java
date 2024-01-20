package br.com.lanchonete.rest.presenters;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.rest.presenters.output.CategoryOutputDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryPresenter {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryOutputDTO mapCategoryOutputDTOFromCategory(Category category) {
        return modelMapper.map(category, CategoryOutputDTO.class);
    }

    public List<CategoryOutputDTO> mapListCategoryOutputDTOFromListCategory(List<Category> categories) {
        return modelMapper.map(categories, new TypeToken<List<CategoryOutputDTO>>() {}.getType());
    }

}
