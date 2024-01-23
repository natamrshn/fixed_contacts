package spring.boot.optic.okulist.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.user.UserLoginRequestDto;
import spring.boot.optic.okulist.dto.user.UserLoginResponseDto;
import spring.boot.optic.okulist.model.RegisteredUser;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto authenticate(UserLoginRequestDto request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtUtil.generateToken(authentication.getName());
        Long userId = (authentication.getPrincipal()
                instanceof RegisteredUser) ? ((RegisteredUser) authentication.getPrincipal()).getId() : null;
        return new UserLoginResponseDto(userId, token);
    }
}
