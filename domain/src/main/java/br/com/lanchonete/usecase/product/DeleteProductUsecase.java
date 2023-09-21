package br.com.lanchonete.usecase.product;

import br.com.lanchonete.exception.ProductActivedException;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.model.StatusActiveType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.DeleteProduct;

import java.util.UUID;

public class DeleteProductUsecase implements DeleteProduct {

    private final LogRepository logRepository;
    private final ProductRepository productRepository;

    public DeleteProductUsecase(ProductRepository productRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void deleteById(UUID id) {
        logRepository.info(DeleteProductUsecase.class, LogCode.LogCodeInfo._0016);
        Product product = productRepository.findById(id);

        if (product.getStatus() == StatusActiveType.ACTIVE) {
            throw new ProductActivedException();
        }

        productRepository.deleteById(id);
        logRepository.info(DeleteProductUsecase.class, LogCode.LogCodeInfo._0017);

    }
}
