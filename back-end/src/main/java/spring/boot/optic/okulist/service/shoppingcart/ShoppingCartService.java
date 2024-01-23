package spring.boot.optic.okulist.service.shoppingcart;

import spring.boot.optic.okulist.dto.shoppingcart.ShoppingCartResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.ShoppingCartItemsRequestDto;

public interface ShoppingCartService {
    CartItemResponseDto addItem(ShoppingCartItemsRequestDto requestDto);

    ShoppingCartResponseDto getShoppingCart(String sessionId);
}
