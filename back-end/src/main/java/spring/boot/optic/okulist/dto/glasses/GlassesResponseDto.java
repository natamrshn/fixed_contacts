package spring.boot.optic.okulist.dto.glasses;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class GlassesResponseDto {
    private Long id;
    private String glassesName;
    private BigDecimal price;
    private String description;
    private String identifier;
    private String color;
    private String model;
    private String manufacturer;
    private String imageUrl;
    private String imageUrlSecond;
    private Set<CategoryResponseDto> categories;
    private List<Variation> variations;

    @Data
    @NoArgsConstructor
    public static class Variation {
        private Long id;
        private String coverImage;
    }
}
