package spring.boot.optic.okulist.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @Positive
    private BigDecimal price;
    @NotNull
    private String identifier;
    private String category;
    private Set<Long> categoryIds;
    @NotNull
    private String imageUrl;
    private String imageUrlSecond;
}
