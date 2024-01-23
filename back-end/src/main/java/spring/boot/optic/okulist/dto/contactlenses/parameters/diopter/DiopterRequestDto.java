package spring.boot.optic.okulist.dto.contactlenses.parameters.diopter;

import lombok.Data;

@Data
public class DiopterRequestDto {
    private String minDiopter;
    private String maxDiopter;
    private String step;
}
