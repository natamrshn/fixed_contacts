package spring.boot.optic.okulist.specification.liquid.builders;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.dto.liquid.LiquidSearchParameter;
import spring.boot.optic.okulist.model.Liquid;
import spring.boot.optic.okulist.specification.glasses.builders.GlassesSpecificationBuilder;
import spring.boot.optic.okulist.specification.liquid.managers.SpecificationProviderManagerLiquid;

@Component
@RequiredArgsConstructor
@Tag(name = "Liquid Specification Builder ",
        description = "Builds Liquid specifications for filtering")
public class LiquidSpecificationBuilder implements SpecificationBuilderLiquid<Liquid> {
    private static final Logger logger = LogManager
            .getLogger(GlassesSpecificationBuilder.class);

    private final SpecificationProviderManagerLiquid<Liquid> specificationProviderManagerGlasses;
    private final Map<String, Specification<Liquid>> specificationCache =
            new ConcurrentHashMap<>();

    @Override
    public Specification<Liquid> build(LiquidSearchParameter searchParametersDto) {
        List<Specification<Liquid>> specifications = new ArrayList<>();
        if (searchParametersDto.volume() != null) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("volume"), searchParametersDto.volume()));
        }

        if (searchParametersDto.name() != null) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("name"), searchParametersDto.name()));
        }

        return specifications.stream().reduce(Specification::and).orElse(null);
    }
}
