package br.com.lanchonete.usecase.product;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.UpdateProduct;
import br.com.lanchonete.port.usecase.product.ValidateUpdateProduct;

public class UpdateProductUsecase implements UpdateProduct {

    private final LogRepository logRepository;
    private final ProductRepository productRepository;
    private final ValidateUpdateProduct validateProduct;

    public UpdateProductUsecase(ProductRepository productRepository, LogRepository logRepository, ValidateUpdateProduct validateProduct) {
        this.logRepository = logRepository;
        this.productRepository = productRepository;
        this.validateProduct = validateProduct;
    }

    @Override
    public Product update(Product product) {
        logRepository.info(UpdateProductUsecase.class, LogCode.LogCodeInfo._0014);
        validateProduct.validate(product);
        Product productSaved = productRepository.update(product);
        logRepository.info(UpdateProductUsecase.class, LogCode.LogCodeInfo._0015);
        return productSaved;
    }

}
