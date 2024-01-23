package spring.boot.optic.okulist.specification.liquid.providers;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProviderLiquid<T> {
    String getKey();

    Specification<T> getLiquidSpecification(String[] params);
}
