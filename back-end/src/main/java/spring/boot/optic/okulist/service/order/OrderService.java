package spring.boot.optic.okulist.service.order;

import java.util.List;
import org.springframework.data.domain.Pageable;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.dto.order.UpdateOrderRequestDto;
import spring.boot.optic.okulist.model.Order;

public interface OrderService {
    OrderResponseDto update(Long id, UpdateOrderRequestDto requestDto);

    OrderResponseDto addOrder(Long id,CreateOrderRequestDto createOrderRequestDto);

    List<OrderResponseDto> getByUserId(Long userId);

    OrderResponseDto findById(Long id);

    List<OrderResponseDto> findAllOrders(Long id, Pageable pageable);

    OrderResponseDto getByOrderIdAndOrderItemId(Long orderId, Long orderItemsId);

    OrderResponseDto updateOrderStatus(Long orderId, Order.Status status);

    List<Order> findAllOrdersSortedByDateDesc();
}
