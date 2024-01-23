package spring.boot.optic.okulist.service.cartitem;

import java.util.Set;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.ShoppingCartItemsRequestDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.UpdateQuantityDto;
import spring.boot.optic.okulist.model.ShoppingCartItem;
import spring.boot.optic.okulist.model.user.User;

public interface CartItemService {
    CartItemResponseDto save(ShoppingCartItemsRequestDto shoppingCartItemsRequestDto);

    Set<CartItemResponseDto> findByShoppingCartId(Long id);

    CartItemResponseDto update(UpdateQuantityDto updateQuantityDto, Long id);

    void setShoppingCartAndCartItems(User user, ShoppingCartItem cartItem);

    void delete(Long cartItemId);
}
