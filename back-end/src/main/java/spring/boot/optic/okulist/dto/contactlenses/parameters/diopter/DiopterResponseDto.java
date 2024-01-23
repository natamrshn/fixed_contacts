package spring.boot.optic.okulist.dto.contactlenses.parameters.diopter;

import lombok.Data;

@Data
public class DiopterResponseDto {
    private Long id;
    private String minDiopter;
    private String maxDiopter;
    private String step;
}
