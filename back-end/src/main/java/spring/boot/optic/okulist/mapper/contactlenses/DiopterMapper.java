package spring.boot.optic.okulist.mapper.contactlenses;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Diopter;

@Mapper(config = MapperConfig.class)
public interface DiopterMapper {

    DiopterResponseDto toDto(Diopter diopter);

    Diopter toModel(DiopterRequestDto diopterRequestDto);
}
