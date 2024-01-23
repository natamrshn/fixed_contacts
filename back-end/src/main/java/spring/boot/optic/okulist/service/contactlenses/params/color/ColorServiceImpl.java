package spring.boot.optic.okulist.service.contactlenses.params.color;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorResponseDto;
import spring.boot.optic.okulist.mapper.contactlenses.ColorMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Color;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.ColorRepository;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    @Override
    public ColorResponseDto createColor(ColorRequestDto colorRequestDto) {
        Color color = colorMapper.toModel(colorRequestDto);
        return colorMapper.toDto(colorRepository.save(color));
    }

    @Override
    public ColorResponseDto getColorById(Long id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Color not found with id: " + id));
        return colorMapper.toDto(color);
    }

    @Override
    public List<ColorResponseDto> getAllColors() {
        List<Color> colors = colorRepository.findAll();
        return colorMapper.toDtoList(colors);
    }
}
