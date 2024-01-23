package spring.boot.optic.okulist.service.contactlenses.params.diopter;

import java.util.List;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterResponseDto;

public interface DiopterService {
    DiopterResponseDto getDiopterById(Long id);

    DiopterResponseDto createDiopter(DiopterRequestDto diopterRequestDto);

    List<DiopterResponseDto> getAllDiopters();
}
