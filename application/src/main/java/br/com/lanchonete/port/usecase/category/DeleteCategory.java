package br.com.lanchonete.port.usecase.category;

import br.com.lanchonete.model.Product;

public interface DeleteCategory {

    Product deleteByName(String name);

}
