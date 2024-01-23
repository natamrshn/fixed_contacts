package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.LenseConfigDto;
import spring.boot.optic.okulist.model.LenseItemConfig;
import spring.boot.optic.okulist.model.ShoppingCartItem;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    CartItemResponseDto toDto(ShoppingCartItem cartItem);

    default LenseItemConfig toLenseItemConfig(LenseConfigDto lenseConfigDto) {
        LenseItemConfig lenseItemConfig = new LenseItemConfig();
        lenseItemConfig.setColor(lenseConfigDto.getColor());
        lenseItemConfig.setCylinder(lenseConfigDto.getCylinder());
        lenseItemConfig.setDegree(lenseConfigDto.getDegree());
        lenseItemConfig.setDiopter(lenseConfigDto.getDiopter());
        lenseItemConfig.setSphere(lenseConfigDto.getSphere());
        return lenseItemConfig;
    }
}
