package spring.boot.optic.okulist.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> getByUserId(Long id);

    @EntityGraph(attributePaths = "cartItems")
    Optional<ShoppingCart> findById(Long id);
}
