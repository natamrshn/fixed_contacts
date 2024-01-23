package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;
import spring.boot.optic.okulist.model.OrderItem;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mappings({
            @Mapping(target = "productId", source = "product.id")
    })
    OrderItemDto toDto(OrderItem orderItem);

    @Mappings({
            @Mapping(target = "userId", source = "order.user.id")
    })
    OrderResponseDto toItemResponseDto(OrderItem orderItem);
}
