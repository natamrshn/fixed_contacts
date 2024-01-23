package spring.boot.optic.okulist.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private String status;
    private String shippingAddress;
    private Set<OrderItemDto> orderItems;
}
