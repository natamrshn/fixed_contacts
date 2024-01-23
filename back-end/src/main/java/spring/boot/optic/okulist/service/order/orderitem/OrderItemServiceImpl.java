package spring.boot.optic.okulist.service.order.orderitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.OrderItemMapper;
import spring.boot.optic.okulist.model.OrderItem;
import spring.boot.optic.okulist.repository.OrderItemRepository;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDto getByProductId(Long productId) {
        OrderItem orderItem = orderItemRepository.findByProductId(productId);
        if (orderItem == null) {
            throw new RuntimeException("Can't found order by product id: " + productId);
        }
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderItemDto findById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't found order by id: " + id));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
