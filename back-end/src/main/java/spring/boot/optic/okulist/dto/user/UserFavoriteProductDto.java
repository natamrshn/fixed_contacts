package spring.boot.optic.okulist.dto.user;

import lombok.Data;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;

@Data
public class UserFavoriteProductDto {
    private Long id;
    private ProductResponseDto productResponseDto;
}
