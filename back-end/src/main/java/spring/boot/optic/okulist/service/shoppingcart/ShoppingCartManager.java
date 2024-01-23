package spring.boot.optic.okulist.service.shoppingcart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.model.ShoppingCart;
import spring.boot.optic.okulist.model.user.User;
import spring.boot.optic.okulist.repository.ShoppingCartRepository;

@Service
@RequiredArgsConstructor
public class ShoppingCartManager {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart registerNewCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public void clearCart(ShoppingCart shoppingCart) {
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);
    }
}
