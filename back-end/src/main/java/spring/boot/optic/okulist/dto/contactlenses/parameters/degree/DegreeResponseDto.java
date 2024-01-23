package spring.boot.optic.okulist.dto.contactlenses.parameters.degree;

import lombok.Data;

@Data
public class DegreeResponseDto {
    private Long id;
    private String minDegree;
    private String maxDegree;
    private String degreeStep;
}
