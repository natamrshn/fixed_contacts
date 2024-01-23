package spring.boot.optic.okulist.service.user.passwordreset;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.exception.VerificationCodeCacheException;
import spring.boot.optic.okulist.model.user.User;
import spring.boot.optic.okulist.repository.UserRepository;
import spring.boot.optic.okulist.service.emailsender.EmailService;

@Service
@RequiredArgsConstructor
@EnableCaching
public class UserPasswordInitiationServiceImpl implements UserPasswordInitiationService {
    private final CacheManager cacheManager;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Override
    public void initiatePasswordChange() {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = authenticatedUser.getEmail();
        String verificationCode = generateVerificationCode();
        Cache cache = cacheManager.getCache("verificationCodes");

        if (cache != null) {
            cache.put(userEmail, verificationCode);
            System.out.println("Verification code stored in the cache for user " + userEmail);
        } else {
            throw new VerificationCodeCacheException("Cache is null. Unable to store verification code.");
        }

        emailService.sendVerificationCodeEmail(userEmail, verificationCode);
        System.out.println("Verification code sent via email to user " + userEmail);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + userId));
    }

    public String getUserEmail(Long userId) {
        return getUserById(userId).getEmail();
    }

    @Override
    public String generateVerificationCode() {
        int length = 6;
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = (int) (Math.random() * 10);
            verificationCode.append(digit);
        }
        return verificationCode.toString();
    }
}
