package spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class ContactLensesRequestDto {
    private Long lensConfigurationId;
    private Set<CategoryResponseDto> categories;
    private String name;
    private BigDecimal price;
    private String identifier;
    private String description;
    private String imageUrl;
    private String imageUrlSecond;
}
