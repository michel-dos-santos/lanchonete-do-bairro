package br.com.lanchonete.port.usecase.product;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.Product;

public interface DeleteProduct {

    Product deleteByCategoryAndName(Category category, String name);

}
