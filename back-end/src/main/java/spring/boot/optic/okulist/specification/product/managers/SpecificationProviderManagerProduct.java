package spring.boot.optic.okulist.specification.product.managers;

import spring.boot.optic.okulist.model.Product;
import spring.boot.optic.okulist.specification.product.providers.SpecificationProviderProduct;

public interface SpecificationProviderManagerProduct<T> {
    SpecificationProviderProduct<Product> getSpecificationProvider(String key);
}
