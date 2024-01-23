package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.model.Order;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mappings({
            @Mapping(target = "userId", source = "user.id")
    })
    OrderResponseDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    Order toModel(CreateOrderRequestDto orderRequestDto);
}
