package spring.boot.optic.okulist.dto.user;

import lombok.Data;

@Data
public class UserPasswordUpdateRequestDto {
    private String password;
    private String verificationCode;
}
