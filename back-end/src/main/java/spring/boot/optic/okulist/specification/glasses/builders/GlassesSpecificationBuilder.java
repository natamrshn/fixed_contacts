package spring.boot.optic.okulist.specification.glasses.builders;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.specification.glasses.managers.SpecificationProviderManagerGlasses;

@Component
@RequiredArgsConstructor
@Tag(name = "Glasses Specification Builder ",
        description = "Builds glasses specifications for filtering")
public class GlassesSpecificationBuilder implements SpecificationBuilderGlasses<Glasses> {
    private static final Logger logger = LogManager
            .getLogger(GlassesSpecificationBuilder.class);

    private final SpecificationProviderManagerGlasses<Glasses> specificationProviderManagerGlasses;
    private final Map<String, Specification<Glasses>> specificationCache =
            new ConcurrentHashMap<>();

    @Operation(summary = "Build glasses specifications",
            description = "Builds specifications for filtering glasses "
                    + " based on search parameters.")
    @Override
    public Specification<Glasses> build(GlassesSearchParameter searchParametersDto) {
        List<Specification<Glasses>> specifications = new ArrayList<>();
        if (StringUtils.isNotBlank(searchParametersDto.color())) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("color"), searchParametersDto.color()));
        }

        if (StringUtils.isNotBlank(searchParametersDto.model())) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("model"), searchParametersDto.model()));
        }

        if (StringUtils.isNotBlank(searchParametersDto.manufacturer())) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("manufacturer"),
                            searchParametersDto.manufacturer()));
        }
        return specifications.stream().reduce(Specification::and).orElse(null);
    }

    private Specification<Glasses> getSpecificationFromCache(String key, String param) {
        String[] params = param.split("-");
        return specificationCache.computeIfAbsent(key, k ->
                specificationProviderManagerGlasses.getSpecificationProvider(k)
                        .getGlassesSpecification(params));
    }
}
