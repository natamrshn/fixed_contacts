package spring.boot.optic.okulist.service.contactlenses.params.cylinder;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderResponseDto;
import spring.boot.optic.okulist.mapper.contactlenses.CylinderMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Cylinder;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.CylinderRepository;

@Service
@RequiredArgsConstructor
public class CylinderServiceImpl implements CylinderService {
    private final CylinderRepository cylinderRepository;
    private final CylinderMapper cylinderMapper;

    @Override
    public CylinderResponseDto createCylinder(CylinderRequestDto cylinderRequestDto) {
        Cylinder cylinder = cylinderMapper.toModel(cylinderRequestDto);
        Cylinder savedCylinder = cylinderRepository.save(cylinder);
        return cylinderMapper.toDto(savedCylinder);
    }

    @Override
    public CylinderResponseDto getCylinderById(Long id) {
        Cylinder cylinder = cylinderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cylinder not found with id: " + id));
        return cylinderMapper.toDto(cylinder);
    }

    @Override
    public List<CylinderResponseDto> getAllCylinders() {
        List<Cylinder> cylinders = cylinderRepository.findAll();
        return cylinders.stream()
                .map(cylinderMapper::toDto)
                .collect(Collectors.toList());
    }
}
