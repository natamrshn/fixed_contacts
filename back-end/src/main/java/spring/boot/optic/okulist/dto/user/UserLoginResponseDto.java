package spring.boot.optic.okulist.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserLoginResponseDto {
    private final Long id;
    private final String token;
}
