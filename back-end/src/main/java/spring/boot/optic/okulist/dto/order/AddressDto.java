package spring.boot.optic.okulist.dto.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressDto {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;
    private boolean isAvailableForPickup;
}
