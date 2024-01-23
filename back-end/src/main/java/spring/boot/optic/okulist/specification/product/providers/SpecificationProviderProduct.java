package spring.boot.optic.okulist.specification.product.providers;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProviderProduct<T> {
    String getKey();

    Specification<T> getProductSpecification(String[] params);
}
