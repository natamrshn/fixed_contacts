package spring.boot.optic.okulist.mapper;

import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;
import spring.boot.optic.okulist.model.Glasses;

@Mapper(config = MapperConfig.class)
public interface GlassesMapper {
    @Mapping(target = "categories", ignore = true)
    GlassesResponseDto toDto(Glasses glasses);

    @Mapping(target = "name", source = "glassesName")
    Glasses toModel(GlassesRequestDto requestDto);

    Glasses toModelSearchParam(GlassesSearchParameter glassesSearchParameter);

    @AfterMapping
    default void mapCategories(@MappingTarget GlassesResponseDto glassesResponseDto,
                               Glasses glasses) {
        glassesResponseDto.setGlassesName(glasses.getName());
        glassesResponseDto.setCategories(glasses.getCategories().stream()
                .map(category -> {
                    CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                    categoryResponseDto.setId(category.getId());
                    categoryResponseDto.setName(category.getName());
                    return categoryResponseDto;
                })
                .collect(Collectors.toSet()));
    }

    default GlassesResponseDto.Variation mapGlassesVariationToDto(Glasses glassesVariation) {
        GlassesResponseDto.Variation variations = new GlassesResponseDto.Variation();
        variations.setId(glassesVariation.getId());
        variations.setCoverImage(glassesVariation.getCoverImage());
        return variations;
    }
}
