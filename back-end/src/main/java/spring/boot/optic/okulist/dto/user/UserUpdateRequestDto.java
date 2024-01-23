package spring.boot.optic.okulist.dto.user;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String firstName;
    private String lastName;
    private Long phoneNumber;
}
