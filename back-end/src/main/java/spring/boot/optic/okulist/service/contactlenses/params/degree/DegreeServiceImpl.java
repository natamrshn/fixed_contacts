package spring.boot.optic.okulist.service.contactlenses.params.degree;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeResponseDto;
import spring.boot.optic.okulist.mapper.contactlenses.DegreeMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Degree;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.DegreeRepository;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {

    private final DegreeRepository degreeRepository;
    private final DegreeMapper degreeMapper;

    @Override
    public DegreeResponseDto createDegree(DegreeRequestDto degreeRequestDto) {
        Degree degree = degreeMapper.toModel(degreeRequestDto);
        Degree savedDegree = degreeRepository.save(degree);
        return degreeMapper.toDto(savedDegree);
    }

    @Override
    public DegreeResponseDto getDegreeById(Long id) {
        Degree degree = degreeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Degree not found with id: " + id));
        return degreeMapper.toDto(degree);
    }

    @Override
    public List<DegreeResponseDto> getAllDegrees() {
        List<Degree> degrees = degreeRepository.findAll();
        return degrees.stream()
                .map(degreeMapper::toDto)
                .collect(Collectors.toList());
    }
}
