package spring.boot.optic.okulist.service.contactlenses.manufacturer;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.contactlenses.ManufacturerMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Color;
import spring.boot.optic.okulist.model.lenses.parameters.Cylinder;
import spring.boot.optic.okulist.model.lenses.parameters.Degree;
import spring.boot.optic.okulist.model.lenses.parameters.Diopter;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;
import spring.boot.optic.okulist.model.lenses.parameters.Sphere;
import spring.boot.optic.okulist.repository.lenses.ManufacturerRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.ColorRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.CylinderRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.DegreeRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.DiopterRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.SphereRepository;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;
    private final DegreeRepository degreeRepository;
    private final DiopterRepository diopterRepository;
    private final SphereRepository sphereRepository;
    private final ColorRepository colorRepository;
    private final CylinderRepository cylinderRepository;

    @Override
    public ManufacturerResponseDto createManufacturer(
            ManufacturerRequestDto manufacturerRequestDto) {
        Manufacturer manufacturer = manufacturerMapper.toModel(manufacturerRequestDto);

        List<Long> requestedColors = manufacturerRequestDto.getColorsIds();
        if (requestedColors != null && !requestedColors.isEmpty()) {
            List<Color> colors = requestedColors.stream()
                    .map(colorRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            if (colors.size() != requestedColors.size()) {
                Set<Long> foundIds = colors.stream()
                        .map(Color::getId)
                        .collect(toSet());

                List<Long> notFoundIds = requestedColors.stream()
                        .filter(id -> !foundIds.contains(id))
                        .toList();

                throw new EntityNotFoundException("Not all lens colors were found. "
                        + "Haven't found colors with ids: " + notFoundIds);
            }

            manufacturer.setColors(colors);
        }

        if (manufacturerRequestDto.getCylinderId() != null) {
            Cylinder cylinder = cylinderRepository.findById(manufacturerRequestDto.getCylinderId())
                    .orElseThrow(() -> new EntityNotFoundException("Cylinder not found with ID: "
                            + manufacturerRequestDto.getCylinderId()));
            manufacturer.setCylinder(cylinder);
        }
        if (manufacturerRequestDto.getDegreeId() != null) {
            Degree degree = degreeRepository.findById(manufacturerRequestDto.getDegreeId())
                    .orElseThrow(() -> new EntityNotFoundException("Degree not found with ID: "
                            + manufacturerRequestDto.getDegreeId()));
            manufacturer.setDegree(degree);
        }

        if (manufacturerRequestDto.getDiopterId() != null) {
            Diopter diopter = diopterRepository.findById(manufacturerRequestDto.getDiopterId())
                    .orElseThrow(() -> new EntityNotFoundException("Diopter not found with ID: "
                            + manufacturerRequestDto.getDiopterId()));
            manufacturer.setDiopter(diopter);
        }

        List<Long> requestedSpheres = manufacturerRequestDto.getSpheresIds();
        if (requestedSpheres != null && !requestedSpheres.isEmpty()) {
            List<Sphere> spheres = requestedSpheres.stream()
                    .map(sphereRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            if (spheres.size() != requestedSpheres.size()) {
                Set<Long> foundIds = spheres.stream()
                        .map(Sphere::getId)
                        .collect(toSet());

                List<Long> notFoundIds = requestedSpheres.stream()
                        .filter(id -> !foundIds.contains(id))
                        .toList();

                throw new EntityNotFoundException("Not all lens spheres were found. "
                        + "Haven't found spheres with ids: " + notFoundIds);
            }

            manufacturer.setSpheres(spheres);
        }

        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.toDto(savedManufacturer);
    }

    @Override
    public ManufacturerResponseDto getManufacturerById(Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found with id: "
                        + manufacturerId));
        return manufacturerMapper.toDto(manufacturer);
    }

    @Override
    public List<ManufacturerResponseDto> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return manufacturers.stream()
                .map(manufacturerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteManufacturer(Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't found liquid with ID :"
                                + manufacturerId)
                );
        manufacturer.setDeleted(true);
        manufacturerRepository.save(manufacturer);
    }
}
