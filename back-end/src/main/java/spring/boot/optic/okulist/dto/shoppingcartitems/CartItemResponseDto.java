package spring.boot.optic.okulist.dto.shoppingcartitems;

import lombok.Data;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;

@Data
public class CartItemResponseDto {
    private Long id;
    private ProductResponseDto product;
    private LenseConfigDto lenseConfig;
    private int quantity;
}
