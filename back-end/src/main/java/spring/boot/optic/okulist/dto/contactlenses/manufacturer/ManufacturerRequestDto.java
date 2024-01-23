package spring.boot.optic.okulist.dto.contactlenses.manufacturer;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ManufacturerRequestDto {
    private String name;
    private List<Long> colorsIds;
    private Long cylinderId;
    private Long degreeId;
    private Long diopterId;
    private List<Long> spheresIds;
}
