package spring.boot.optic.okulist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.optic.okulist.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByProductId(Long productId);
}
