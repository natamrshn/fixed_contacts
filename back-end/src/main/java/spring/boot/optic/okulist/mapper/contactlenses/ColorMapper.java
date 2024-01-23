package spring.boot.optic.okulist.mapper.contactlenses;

import java.util.List;
import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Color;

@Mapper(config = MapperConfig.class)
public interface ColorMapper {

    ColorResponseDto toDto(Color color);

    Color toModel(ColorRequestDto colorRequestDto);

    List<ColorResponseDto> toDtoList(List<Color> colors);
}
