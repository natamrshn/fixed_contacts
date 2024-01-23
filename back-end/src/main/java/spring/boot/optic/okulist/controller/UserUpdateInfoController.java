package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.user.UserPasswordUpdateRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;
import spring.boot.optic.okulist.dto.user.UserUpdateRequestDto;
import spring.boot.optic.okulist.service.user.UserService;
import spring.boot.optic.okulist.service.user.passwordreset.UserPasswordInitiationService;
import spring.boot.optic.okulist.service.user.passwordreset.UserPasswordUpdateService;

@Tag(name = "Authentication management", description = "Endpoints for managing authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserUpdateInfoController {
    private final UserService userService;
    private final UserPasswordInitiationService initiationService;
    private final UserPasswordUpdateService updateService;

    @PatchMapping("/{userId}/update")
    @Operation(summary = "Update user profile")
    @ApiResponse(responseCode = "200", description = "User profile updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public UserResponseDto updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequestDto updateRequestDto) {
        return userService.update(userId, updateRequestDto);
    }

    @PostMapping("/start")
    @Operation(summary = "Initiate password change")
    @ApiResponse(responseCode = "200", description = "Password change initiated successfully")
    public ResponseEntity<String> initiatePasswordChange() {
        initiationService.initiatePasswordChange();
        return ResponseEntity.ok("Password change initiated successfully.");
    }

    @PostMapping("/confirm")
    @Operation(summary = "Confirm password change")
    @ApiResponse(responseCode = "200", description = "Password change confirmed successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserResponseDto> confirmPasswordChange(
            @RequestBody UserPasswordUpdateRequestDto updateRequestDto) {
        UserResponseDto responseDto = updateService.updatePassword(updateRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
