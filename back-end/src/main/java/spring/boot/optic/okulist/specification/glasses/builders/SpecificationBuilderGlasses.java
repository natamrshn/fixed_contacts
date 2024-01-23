package spring.boot.optic.okulist.specification.glasses.builders;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;

public interface SpecificationBuilderGlasses<T> {
    Specification<T> build(GlassesSearchParameter searchParametersDto);
}
