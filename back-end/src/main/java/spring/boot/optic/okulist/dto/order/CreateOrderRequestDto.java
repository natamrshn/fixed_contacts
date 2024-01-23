package spring.boot.optic.okulist.dto.order;

import lombok.Data;

@Data
public class CreateOrderRequestDto {
    private boolean manualAddressInput;
    private String shippingAddress;
    private String sessionId;
    private Long phoneNumber;
    private String firstName;
    private String lastName;
    private Long chosenAddressId; // Оновлене поле
}
