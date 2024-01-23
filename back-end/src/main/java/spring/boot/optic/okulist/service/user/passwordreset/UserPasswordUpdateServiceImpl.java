package spring.boot.optic.okulist.service.user.passwordreset;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.user.UserPasswordUpdateRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.exception.VerificationCodeCacheException;
import spring.boot.optic.okulist.exception.VerificationCodeMismatchException;
import spring.boot.optic.okulist.mapper.UserMapper;
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.model.user.User;
import spring.boot.optic.okulist.repository.UserRepository;
import spring.boot.optic.okulist.service.emailsender.EmailService;

@Service
@RequiredArgsConstructor
@EnableCaching
public class UserPasswordUpdateServiceImpl implements UserPasswordUpdateService {
    private final PasswordEncoder passwordEncoder;
    private final CacheManager cacheManager;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public UserResponseDto updatePassword(UserPasswordUpdateRequestDto updateRequestDto) {
        String email = getEmailFromSecurityContext();
        verifyCodeAndChangePassword(email, updateRequestDto.getVerificationCode(),
                updateRequestDto.getPassword());
        RegisteredUser user = getUserByEmail(email);
        UserResponseDto responseDto = userMapper.toDto(user);
        emailService.sendPasswordChangeConfirmation(email);
        return responseDto;
    }

    @Override
    public void verifyCodeAndChangePassword(String email, String verificationCode,
                                            String newPassword) {
        String storedCode = getVerificationCode(email);
        System.out.println("Provided Code: " + verificationCode);
        System.out.println("Stored Code: " + storedCode);
        if (storedCode != null && storedCode.equals(verificationCode)) {
            RegisteredUser user = getUserByEmail(email);
            user.setPassword(passwordEncoder.encode(newPassword));
            clearVerificationCode(email);
            userRepository.save(user);
        } else {
            throw new VerificationCodeMismatchException("Invalid verification code");
        }
    }

    private String getVerificationCode(String email) {
        Cache cache = cacheManager.getCache("verificationCodes");
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(email);
            if (valueWrapper != null) {
                return Objects.requireNonNull(valueWrapper.get()).toString();
            }
        }
        throw new VerificationCodeCacheException("Cache is null or verification code not found for email: "
                + email);
    }

    private void clearVerificationCode(String email) {
        Cache cache = cacheManager.getCache("verificationCodes");
        if (cache != null) {
            cache.evict(email);
        } else {
            throw new VerificationCodeCacheException("Cache is null. Unable to clear verification code for email: "
                    + email);
        }
    }

    private RegisteredUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with email: " + email));
    }

    private String getEmailFromSecurityContext() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
    }
}
