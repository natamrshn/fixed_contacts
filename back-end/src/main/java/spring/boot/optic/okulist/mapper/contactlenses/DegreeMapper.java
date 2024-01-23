package spring.boot.optic.okulist.mapper.contactlenses;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Degree;

@Mapper(config = MapperConfig.class)
public interface DegreeMapper {
    DegreeResponseDto toDto(Degree degree);

    Degree toModel(DegreeRequestDto degreeRequestDto);
}
