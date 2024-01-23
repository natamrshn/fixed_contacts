package spring.boot.optic.okulist.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.boot.optic.okulist.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    @Query("SELECT o FROM Order o WHERE o.id = :order_id"
            + " AND EXISTS (SELECT oi FROM o.orderItems oi WHERE oi.id = :order_item_id)")
    Optional<Order> findByOrderIdAndOrderItemsId(@Param("order_id") Long orderId,
                                                 @Param("order_item_id") Long orderItemsId);

    List<Order> findAllByUserEmail(String email);

    @Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
    List<Order> findAllOrdersSortedByDateDesc();

    @Query("SELECT  o FROM Order o LEFT JOIN FETCH o.orderItems"
            + " LEFT JOIN FETCH o.user u WHERE u.id = :userId")
    List<Order> findAllOrders(long userId);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findById(Long id);
}
