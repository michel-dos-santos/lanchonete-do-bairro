package br.com.lanchonete.port.usecase.product;

import java.util.UUID;

public interface DeleteProduct {

    void deleteById(UUID id);

}
