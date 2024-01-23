package spring.boot.optic.okulist.dto.liquid;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class LiquidRequestDto {
    private String name;
    @Positive
    private int volume;
    @Positive
    private BigDecimal price;
    private String description;
    @NotNull
    private String identifier;
    private String imageUrl;
    private String imageUrlSecond;
    private Set<CategoryResponseDto> categories;
    private String coverImage;
}
