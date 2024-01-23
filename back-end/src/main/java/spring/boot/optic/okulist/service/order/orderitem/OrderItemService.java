package spring.boot.optic.okulist.service.order.orderitem;

import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;

public interface OrderItemService {
    OrderItemDto getByProductId(Long bookId);

    OrderItemDto findById(Long id);

    void deleteById(Long id);
}
