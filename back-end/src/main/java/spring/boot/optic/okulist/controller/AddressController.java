package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.order.AddressDto;
import spring.boot.optic.okulist.service.order.AddressService;

import java.util.Map;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
@Tag(name = "Addresses", description = "Manage addresses")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/{addressId}")
    @Operation(
            summary = "Get Address by ID",
            description = "Get details of a specific address by its ID."
    )
    @ApiResponse(responseCode = "200", description = "Address details retrieved successfully")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Long addressId) {
        AddressDto addressDto = addressService.getById(addressId);
        return ResponseEntity.ok(addressDto);
    }

    @GetMapping("/pickup")
    @Operation(
            summary = "Get Available Pickup Address",
            description = "Get details of an available address for pickup."
    )
    @ApiResponse(responseCode = "200", description = "Available pickup address details retrieved successfully")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<AddressDto> getAvailablePickupAddress() {
        AddressDto addressDto = addressService.getAvailablePickupAddress();
        return ResponseEntity.ok(addressDto);
    }

    @PostMapping
    @Operation(
            summary = "Create Address",
            description = "Create a new address."
    )
    @ApiResponse(responseCode = "201", description = "Address created successfully")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<AddressDto> createAddress(@RequestBody @Valid AddressDto addressDto) {
        AddressDto createdAddressDto = addressService.createAddress(addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddressDto);
    }

    @DeleteMapping("/{addressId}")
    @Operation(
            summary = "Delete Address",
            description = "Delete a specific address by its ID."
    )
    @ApiResponse(responseCode = "204", description = "Address deleted successfully")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{addressId}/availability")
    @Operation(
            summary = "Update Address Availability",
            description = "Update the availability of a specific address for pickup."
    )
    @ApiResponse(responseCode = "200", description = "Address availability updated successfully")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<AddressDto> updateAddressAvailability(
            @PathVariable Long addressId,
            @RequestBody Map<String, Boolean> requestBody) {
        boolean isAvailableForPickup = requestBody.get("isAvailableForPickup");
        AddressDto updatedAddressDto = addressService.updateAddressAvailability(addressId, isAvailableForPickup);
        return ResponseEntity.ok(updatedAddressDto);
    }
}
