package spring.boot.optic.okulist.mapper.contactlenses;

import java.util.Collections;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Color;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;
import spring.boot.optic.okulist.model.lenses.parameters.Sphere;

@Mapper(config = MapperConfig.class,
        uses = {ColorMapper.class,
                CylinderMapper.class,
                DegreeMapper.class,
                DiopterMapper.class})
public interface ManufacturerMapper {

    @Mapping(source = "cylinder.id", target = "cylinderId")
    @Mapping(source = "degree.id", target = "degreeId")
    @Mapping(source = "diopter.id", target = "diopterId")
    ManufacturerResponseDto toDto(Manufacturer manufacturer);

    default List<Long> mapColorsToIds(List<Color> colors) {
        if (colors != null) {
            return colors.stream()
                    .map(Color::getId)
                    .toList();
        }
        return Collections.emptyList();
    }

    default List<Long> mapSpheresToIds(List<Sphere> spheres) {
        if (spheres != null) {
            return spheres.stream()
                    .map(Sphere::getId)
                    .toList();
        }
        return Collections.emptyList();
    }

    @AfterMapping
    default void addColorAndSphereIds(Manufacturer manufacturer,
                                      @MappingTarget ManufacturerResponseDto dto) {
        dto.setColorsId(mapColorsToIds(manufacturer.getColors()));
        dto.setSphereId(mapSpheresToIds(manufacturer.getSpheres()));
    }

    Manufacturer toModel(ManufacturerRequestDto manufacturerRequestDto);
}
