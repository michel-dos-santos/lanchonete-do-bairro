package br.com.lanchonete.port.usecase.product;

import br.com.lanchonete.model.Product;

import java.util.List;
import java.util.UUID;

public interface FindProductsByCategoryID {

    List<Product> findByCategoryID(UUID categoryID);

}
