package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.service.contactlenses.manufacturer.ManufacturerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @Operation(summary = "Create a Manufacturer",
            description = "Creates a new manufacturer of contact lenses.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Manufacturer created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided.")
    public ManufacturerResponseDto createManufacturer(
            @RequestBody @Valid ManufacturerRequestDto manufacturerRequestDto) {
        return manufacturerService.createManufacturer(manufacturerRequestDto);
    }

    @Operation(summary = "Get All Manufacturers",
            description = "Retrieves a list of all manufacturers of contact lenses.")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of manufacturers retrieved successfully")
    public List<ManufacturerResponseDto> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }

    @Operation(summary = "Get Manufacturer by ID",
            description = "Retrieves a specific manufacturer of contact lenses by ID.")
    @GetMapping("/{manufacturerId}")
    @ApiResponse(responseCode = "200", description = "Manufacturer retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Manufacturer not found")
    public ManufacturerResponseDto getManufacturerById(@PathVariable Long manufacturerId) {
        return manufacturerService.getManufacturerById(manufacturerId);
    }

    @Operation(summary = "Delete Manufacturer by ID",
            description = "Deletes a specific manufacturer of contact lenses by ID.")
    @DeleteMapping("/{manufacturerId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "204", description = "Manufacturer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Manufacturer not found")
    public void deleteManufacturer(@PathVariable Long manufacturerId) {
        manufacturerService.deleteManufacturer(manufacturerId);
    }
}

