package spring.boot.optic.okulist.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.model.UserFavoriteProduct;

@Repository
public interface UserFavoriteProductRepository extends JpaRepository<UserFavoriteProduct, Long> {
    Optional<UserFavoriteProduct> findByUserAndProduct_Id(RegisteredUser user, Long productId);
}
