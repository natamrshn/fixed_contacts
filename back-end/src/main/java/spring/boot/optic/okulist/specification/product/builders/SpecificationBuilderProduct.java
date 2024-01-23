package spring.boot.optic.okulist.specification.product.builders;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.dto.product.ProductSearchParameter;

public interface SpecificationBuilderProduct<T> {
    Specification<T> build(ProductSearchParameter searchParametersDto);
}
