package spring.boot.optic.okulist.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.ShoppingCartItem;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    Set<ShoppingCartItem> findCartItemByShoppingCartId(@Param("shoppingCartId") Long shoppingCart);
}
