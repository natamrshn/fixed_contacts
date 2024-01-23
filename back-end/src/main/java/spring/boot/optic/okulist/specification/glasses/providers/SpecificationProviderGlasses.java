package spring.boot.optic.okulist.specification.glasses.providers;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProviderGlasses<T> {
    String getKey();

    Specification<T> getGlassesSpecification(String[] params);
}
