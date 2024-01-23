package spring.boot.optic.okulist.specification.product.builders;

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
import spring.boot.optic.okulist.dto.product.ProductSearchParameter;
import spring.boot.optic.okulist.model.Product;
import spring.boot.optic.okulist.specification.product.managers.SpecificationProviderManagerProduct;

@Component
@RequiredArgsConstructor
@Tag(name = "Product Specification Builder ",
        description = "Builds product specifications for filtering")
public class ProductSpecificationBuilder implements SpecificationBuilderProduct<Product> {
    private static final Logger logger = LogManager
            .getLogger(ProductSpecificationBuilder.class);
    private final SpecificationProviderManagerProduct<Product> specificationProviderManagerProduct;
    private final Map<String, Specification<Product>> specificationCache =
            new ConcurrentHashMap<>();

    @Operation(summary = "Build product specifications",
            description = "Builds specifications for filtering product "
                    + " based on search parameters.")
    @Override
    public Specification<Product> build(ProductSearchParameter searchParametersDto) {
        List<Specification<Product>> specifications = new ArrayList<>();
        if (StringUtils.isNotBlank(searchParametersDto.name())) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("name"), searchParametersDto.name()));
        }

        if (StringUtils.isNotBlank(searchParametersDto.identifier())) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("identifier"),
                            searchParametersDto.identifier()));
        }

        if (searchParametersDto.price() != null) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("price"), searchParametersDto.price()));
        }

        if (searchParametersDto.categoryIds() != null
                && !searchParametersDto.categoryIds().isEmpty()) {
            specifications.add((root, query, criteriaBuilder) ->
                    root.get("category_id").in(searchParametersDto.categoryIds()));
        }
        return specifications.stream().reduce(Specification::and).orElse(null);
    }

    private Specification<Product> getSpecificationFromCache(String key, String param) {
        String[] params = param.split("-");
        return specificationCache.computeIfAbsent(key, k ->
                specificationProviderManagerProduct.getSpecificationProvider(k)
                        .getProductSpecification(params));
    }
}

