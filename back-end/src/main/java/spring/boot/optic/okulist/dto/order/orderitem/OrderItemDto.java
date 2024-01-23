package spring.boot.optic.okulist.dto.order.orderitem;

import lombok.Data;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.LenseConfigDto;

@Data
public class OrderItemDto {
    private Long id;
    private Long productId;
    private ProductResponseDto product;
    private LenseConfigDto lenseConfig;
    private int quantity;
}
