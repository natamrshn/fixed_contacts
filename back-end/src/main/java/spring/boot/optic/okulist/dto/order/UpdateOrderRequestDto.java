package spring.boot.optic.okulist.dto.order;

import lombok.Data;
import spring.boot.optic.okulist.model.Order;

@Data
public class UpdateOrderRequestDto {
    private Order.Status status;
}
