package spring.boot.optic.okulist.mapper;

import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.liquid.LiquidRequestDto;
import spring.boot.optic.okulist.dto.liquid.LiquidResponseDto;
import spring.boot.optic.okulist.dto.liquid.LiquidSearchParameter;
import spring.boot.optic.okulist.model.Category;
import spring.boot.optic.okulist.model.Liquid;

@Mapper(config = MapperConfig.class)
public interface LiquidMapper {
    @Mapping(target = "categories", ignore = true)
    LiquidResponseDto toDto(Liquid liquid);

    Liquid toModel(LiquidRequestDto liquidRequestDto);

    Liquid toModelSearchParam(LiquidSearchParameter liquidSearchParameter);

    @AfterMapping
    default void mapCategories(@MappingTarget LiquidResponseDto liquidResponseDto,
                               Liquid liquid) {
        liquidResponseDto.setName(liquid.getName());
        liquidResponseDto.setCategories(liquid.getCategories().stream()
                .map(this::mapCategoryToDto)
                .collect(Collectors.toSet()));
    }

    default CategoryResponseDto mapCategoryToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }

    default LiquidResponseDto.Variations mapLiquidVariationToDto(Liquid liquidVariation) {
        LiquidResponseDto.Variations variations = new LiquidResponseDto.Variations();
        variations.setId(liquidVariation.getId());
        variations.setCoverImage(liquidVariation.getCoverImage());
        return variations;
    }
}
