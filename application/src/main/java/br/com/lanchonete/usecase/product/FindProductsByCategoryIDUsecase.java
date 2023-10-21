package br.com.lanchonete.usecase.product;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.FindProductsByCategoryID;

import java.util.List;
import java.util.UUID;

public class FindProductsByCategoryIDUsecase implements FindProductsByCategoryID {

    private final LogRepository logRepository;
    private final ProductRepository productRepository;

    public FindProductsByCategoryIDUsecase(ProductRepository productRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findByCategoryID(UUID categoryID) {
        logRepository.info(FindProductsByCategoryIDUsecase.class, LogCode.LogCodeInfo._0019);
        List<Product> products = productRepository.findByCategoryID(categoryID);
        logRepository.info(FindProductsByCategoryIDUsecase.class, LogCode.LogCodeInfo._0020);
        return products;
    }
}
