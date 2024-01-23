package spring.boot.optic.okulist.specification.liquid.builders;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.dto.liquid.LiquidSearchParameter;

public interface SpecificationBuilderLiquid<T> {
    Specification<T> build(LiquidSearchParameter searchParametersDto);
}
