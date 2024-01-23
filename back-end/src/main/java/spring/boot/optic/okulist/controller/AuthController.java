package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.dto.user.UserLoginRequestDto;
import spring.boot.optic.okulist.dto.user.UserLoginResponseDto;
import spring.boot.optic.okulist.dto.user.UserRegistrationRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;
import spring.boot.optic.okulist.exception.AuthenticationException;
import spring.boot.optic.okulist.exception.RegistrationException;
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.security.AuthenticationService;
import spring.boot.optic.okulist.service.user.UserService;

@Tag(name = "Authentication management", description = "Endpoints for managing authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register", description = "Register")
    @ApiResponse(responseCode = "200", description = "User registered successfully")
    @ApiResponse(responseCode = "400", description = "Bad request. Registration failed.")
    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        logger.info("Received registration request for user with email: {}", requestDto.getEmail());
        return userService.register(requestDto);
    }

    @Operation(summary = "Login", description = "Login")
    @ApiResponse(responseCode = "200", description = "User logged in successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized. Login failed.")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/add-favorite-product/{productId}")
    public ResponseEntity<ProductResponseDto> addFavouriteProduct(Authentication authentication,
                                                                  @PathVariable Long productId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User should be authenticated");
        }
        RegisteredUser user = (RegisteredUser) authentication.getPrincipal();
        ProductResponseDto addedProduct = userService.addFavouriteProduct(user, productId);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);
    }

    @GetMapping("/favorite-products")
    public ResponseEntity<Set<ProductResponseDto>> getFavoriteProducts(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User should be authenticated");
        }
        RegisteredUser user = (RegisteredUser) authentication.getPrincipal();
        Set<ProductResponseDto> favoriteProducts = userService.getFavoriteProducts(user.getId());
        return new ResponseEntity<>(favoriteProducts, HttpStatus.OK);
    }

    @DeleteMapping("/delete-favorite-product/{productId}")
    public ResponseEntity<Void> deleteFavoriteProduct(Authentication authentication,
                                                      @PathVariable Long productId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User should be authenticated");
        }
        RegisteredUser user = (RegisteredUser) authentication.getPrincipal();
        userService.deleteFavoriteProduct(user.getId(), productId);
        return ResponseEntity.noContent().build();
    }
}
