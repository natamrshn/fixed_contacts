package spring.boot.optic.okulist.dto.contactlenses.parameters.degree;

import lombok.Data;

@Data
public class DegreeRequestDto {
    private String minDegree;
    private String maxDegree;
    private String degreeStep;
}
