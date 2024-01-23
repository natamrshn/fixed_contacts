package spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder;

import lombok.Data;

@Data
public class CylinderResponseDto {
    private Long id;
    private String minCylinder;
    private String maxCylinder;
    private String cylinderStep;
}
