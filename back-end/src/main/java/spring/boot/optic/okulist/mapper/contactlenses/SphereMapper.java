package spring.boot.optic.okulist.mapper.contactlenses;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Sphere;

@Mapper(config = MapperConfig.class)
public interface SphereMapper {
    SphereResponseDto toDto(Sphere sphere);

    Sphere toModel(SphereRequestDto sphereRequestDto);
}
