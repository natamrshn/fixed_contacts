package spring.boot.optic.okulist.dto.product;

import java.math.BigDecimal;
import java.util.Set;

public record ProductSearchParameter(
        String name,
        BigDecimal price,
        String identifier,
        Set<Long> categoryIds
) {
}
