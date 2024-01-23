package spring.boot.optic.okulist.service.user.passwordreset;

import spring.boot.optic.okulist.dto.user.UserPasswordUpdateRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;

public interface UserPasswordUpdateService {
    UserResponseDto updatePassword(UserPasswordUpdateRequestDto updateRequestDto);

    void verifyCodeAndChangePassword(String email,
                                     String verificationCode,
                                     String newPassword);
}
