package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.service.contactlenses.ContactLensesService;

@Tag(name = "Lenses Controller",
        description = "Endpoints for managing lenses")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/contact-lenses")
public class ContactLensesController {
    private static final Logger logger = LogManager.getLogger(ContactLensesController.class);
    private final ContactLensesService contactLensesService;

    @Operation(summary = "Create a Lenses",
            description = "Creates a new Lenses in shop list.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Lenses created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided.")
    public ContactLensesResponseDto creatLenses(@RequestBody
                                                @Valid ContactLensesRequestDto contactLensesRequestDto) {
        logger.info("Creating a new lenses.");
        return contactLensesService.createContactLenses(contactLensesRequestDto);
    }

    @Operation(summary = "Get All lenses ")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of lenses retrieved successfully")
    public List<ContactLensesResponseDto> getAll(Pageable pageable) {
        return contactLensesService.findAll(pageable);
    }

    @Operation(summary = "Get Lenses by ID")
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Lenses retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Lenses not found")
    public ContactLensesResponseDto getLensesById(@PathVariable Long id) {
        logger.info("Retrieving category with ID: " + id);
        return contactLensesService.getById(id);
    }

    @Operation(summary = "Update Lenses by ID")
    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Lenses updated successfully")
    @ApiResponse(responseCode = "404", description = "Lenses not found")
    public ContactLensesResponseDto updateLenses(@PathVariable Long id,
                                                 @RequestBody
                                                 ContactLensesRequestDto contactLensesRequestDto) {
        logger.info("Updating category with ID: " + id);
        return contactLensesService.update(id, contactLensesRequestDto);
    }

    @Operation(summary = "Delete Lenses by their ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "204", description = "Lenses deleted successfully")
    @ApiResponse(responseCode = "404", description = "Lenses not found")
    public void deleteLenses(@PathVariable Long id) {
        logger.info("Deleting lenses with ID: " + id);
        contactLensesService.deleteLensesById(id);
    }
}
