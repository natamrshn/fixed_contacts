package spring.boot.optic.okulist.dto.shoppingcart;

import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;

@Data
public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    private Set<CartItemResponseDto> cartItems;
}
