package spring.boot.optic.okulist.mapper.contactlenses;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Cylinder;

@Mapper(config = MapperConfig.class)
public interface CylinderMapper {

    CylinderResponseDto toDto(Cylinder cylinder);

    Cylinder toModel(CylinderRequestDto cylinderRequestDto);
}
