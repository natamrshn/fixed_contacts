package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.admin.AdminIdsRequestDto;
import spring.boot.optic.okulist.service.admin.AdminService;

@RestController
@RequestMapping("/admin/permissions")
@RequiredArgsConstructor
@Tag(name = "Admin Permissions", description = "Manage permissions for the ADMIN role")
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);
    private final AdminService adminService;

    @Operation(
            summary = "Grant Permissions to All Admins",
            description = "Grant the create, update, and delete permissions to all administrators."
    )
    @ApiResponse(responseCode = "200", description = "Permissions granted to all ADMINs")
    @PostMapping("/grant/all")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<String> grantPermissionsToAllAdmins() {
        logger.info("Granting permissions to all admins");
        adminService.grantPermissionsToAdmins();
        return ResponseEntity.ok("Permissions granted to all ADMINs");
    }

    @ApiResponse(responseCode = "200", description = "Create, update, and delete permissions revoked from all admins")
    @PostMapping("/revoke-all")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<String> revokeAllPermissionsFromAdmins() {
        logger.info("Revoking permissions from all admins");
        adminService.revokePermissionsFromAdmins();
        return ResponseEntity.ok("Create, update, and delete permissions revoked from all admins");
    }

    @Operation(
            summary = "Grant Permissions to Specific Admins",
            description = "Grant the create, update, and delete permissions to specific administrators."
    )
    @ApiResponse(responseCode = "200", description = "Permissions granted to specific ADMINs")
    @PostMapping("/grant/specific")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<String> grantPermissionsToSpecificAdmins(
            @RequestBody AdminIdsRequestDto adminIdsRequest) {
        List<Long> adminIds = adminIdsRequest.getAdminIds();
        logger.info("Granting permissions to specific admins: {}", adminIds);
        adminService.grantPermissionsToSpecificAdmins(adminIds);
        return ResponseEntity.ok("Permissions granted to specific ADMINs");
    }

    @Operation(
            summary = "Revoke Permissions from Specific Admin",
            description = "Revoke the create, update, and delete permissions from a specific administrator."
    )
    @ApiResponse(responseCode = "200", description = "Permissions revoked from specific ADMINs")
    @PostMapping("/revoke/specific")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<String> revokePermissionsFromSpecificAdmin(
            @RequestBody AdminIdsRequestDto adminIdsRequest) {
        List<Long> adminIds = adminIdsRequest.getAdminIds();
        logger.info("Revoking permissions from specific admins: {}", adminIds);
        adminService.revokePermissionsFromSpecificAdmin(adminIds);
        return ResponseEntity.ok("Permissions revoked from specific ADMINs");
    }
}
