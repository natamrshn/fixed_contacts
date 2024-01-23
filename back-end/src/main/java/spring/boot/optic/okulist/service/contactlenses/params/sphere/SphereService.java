package spring.boot.optic.okulist.service.contactlenses.params.sphere;

import java.util.List;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereResponseDto;

public interface SphereService {
    SphereResponseDto getSphereById(Long id);

    SphereResponseDto createSphere(SphereRequestDto sphereRequestDto);

    List<SphereResponseDto> getAllSpheres();
}
