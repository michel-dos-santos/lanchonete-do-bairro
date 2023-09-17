package br.com.lanchonete.port.usecase.product;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.Product;

import java.util.List;

public interface FindProduct {

    List<Product> findByCategory(Category category);

}
