package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.user.UserFavoriteProductDto;
import spring.boot.optic.okulist.model.UserFavoriteProduct;

@Mapper(config = MapperConfig.class)
public interface UserFavoriteProductMapper {
    @Mapping(source = "product", target = "productResponseDto")
    UserFavoriteProductDto toDto(UserFavoriteProduct userFavoriteProduct);

    @Mapping(source = "productResponseDto", target = "product")
    UserFavoriteProduct toEntity(UserFavoriteProductDto userFavoriteProductDto);
}
