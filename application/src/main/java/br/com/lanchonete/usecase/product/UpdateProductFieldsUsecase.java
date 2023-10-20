package br.com.lanchonete.usecase.product;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.UpdateProductFields;
import br.com.lanchonete.port.usecase.product.ValidateUpdateProduct;

import java.util.Objects;

public class UpdateProductFieldsUsecase implements UpdateProductFields {

    private final LogRepository logRepository;
    private final ProductRepository productRepository;
    private final ValidateUpdateProduct validateProduct;

    public UpdateProductFieldsUsecase(ProductRepository productRepository, LogRepository logRepository, ValidateUpdateProduct validateProduct) {
        this.logRepository = logRepository;
        this.productRepository = productRepository;
        this.validateProduct = validateProduct;
    }

    @Override
    public Product update(Product product) {
        logRepository.info(UpdateProductFieldsUsecase.class, LogCode.LogCodeInfo._0018);
        Product productFound = productRepository.findById(product.getId());

        productFound.setName(Objects.isNull(product.getName()) ? productFound.getName() : product.getName());
        productFound.setDescription(Objects.isNull(product.getDescription()) ? productFound.getDescription() : product.getDescription());
        productFound.setImage(Objects.isNull(product.getImage()) ? productFound.getImage() : product.getImage());
        productFound.setUnitPrice(Objects.isNull(product.getUnitPrice()) ? productFound.getUnitPrice() : product.getUnitPrice());
        productFound.setStatus(Objects.isNull(product.getStatus()) ? productFound.getStatus() : product.getStatus());

        if (!Objects.isNull(product.getCategory()) && !Objects.isNull(product.getCategory().getName())) {
            productFound.getCategory().setName(product.getCategory().getName());
        }

        validateProduct.validate(productFound);
        Product productSaved = productRepository.update(productFound);
        logRepository.info(UpdateProductFieldsUsecase.class, LogCode.LogCodeInfo._0015);
        return productSaved;
    }

}
