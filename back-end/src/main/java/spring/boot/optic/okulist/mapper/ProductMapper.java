package spring.boot.optic.okulist.mapper;

import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.product.ProductRequestDto;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.model.Product;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    @Mapping(target = "categories", ignore = true)
    ProductResponseDto toDto(Product product);

    Product toModel(ProductRequestDto productRequestDto);

    @AfterMapping
    default void mapCategories(@MappingTarget ProductResponseDto productResponseDto,
                               Product product) {
        productResponseDto.setCategories(product.getCategories().stream()
                .map(category -> {
                    CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                    categoryResponseDto.setId(category.getId());
                    categoryResponseDto.setName(category.getName());
                    return categoryResponseDto;
                })
                .collect(Collectors.toSet()));
    }
}
