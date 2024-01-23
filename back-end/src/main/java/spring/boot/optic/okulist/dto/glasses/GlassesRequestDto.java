package spring.boot.optic.okulist.dto.glasses;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class GlassesRequestDto {
    @NotNull
    private String glassesName;
    @Positive
    private BigDecimal price;
    private String description;
    @NotNull
    private String identifier;
    @NotNull
    private String color;
    @NotNull
    private String model;
    private String manufacturer;
    private String imageUrl;
    private String imageUrlSecond;
    private Set<CategoryResponseDto> categories;
    private String coverImage;
}
