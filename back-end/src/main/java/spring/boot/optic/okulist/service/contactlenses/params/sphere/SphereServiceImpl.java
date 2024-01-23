package spring.boot.optic.okulist.service.contactlenses.params.sphere;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereResponseDto;
import spring.boot.optic.okulist.mapper.contactlenses.SphereMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Sphere;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.SphereRepository;

@Service
@RequiredArgsConstructor
public class SphereServiceImpl implements SphereService {
    private final SphereRepository sphereRepository;
    private final SphereMapper sphereMapper;

    @Override
    public SphereResponseDto getSphereById(Long id) {
        Sphere sphere = sphereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sphere not found with id: " + id));
        return sphereMapper.toDto(sphere);
    }

    @Override
    public SphereResponseDto createSphere(SphereRequestDto sphereRequestDto) {
        Sphere sphere = sphereMapper.toModel(sphereRequestDto);
        Sphere savedSphere = sphereRepository.save(sphere);
        return sphereMapper.toDto(savedSphere);
    }

    @Override
    public List<SphereResponseDto> getAllSpheres() {
        List<Sphere> spheres = sphereRepository.findAll();
        return spheres.stream()
                .map(sphereMapper::toDto)
                .collect(Collectors.toList());
    }
}
