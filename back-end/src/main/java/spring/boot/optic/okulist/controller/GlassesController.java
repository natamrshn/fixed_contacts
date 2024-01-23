package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;
import spring.boot.optic.okulist.service.glasses.GlassesService;

@Tag(name = "Glasses Controller",
        description = "Endpoints for managing glasses")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/glasses")
public class GlassesController {
    private static final Logger logger = LogManager.getLogger(GlassesController.class);
    private final GlassesService glassesService;

    @Operation(summary = "Create a Glasses",
            description = "Creates a new Glasses in shop list.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Glasses created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided.")
    public GlassesResponseDto creatGlasses(@RequestBody
                                           @Valid GlassesRequestDto glassesRequestDto) {
        logger.info("Creating a new glasses.");
        return glassesService.save(glassesRequestDto);
    }

    @Operation(summary = "Get All glasses ")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of glasses retrieved successfully")
    public List<GlassesResponseDto> getAll(Pageable pageable) {
        return glassesService.findAll(pageable);
    }

    @Operation(summary = "Get Glasses by ID")
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Glasses retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Glasses not found")
    public GlassesResponseDto getGlassesById(@PathVariable Long id) {
        logger.info("Retrieving glasses with ID: " + id);
        return glassesService.getById(id);
    }

    @Operation(summary = "Update Glasses by ID")
    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Glasses updated successfully")
    @ApiResponse(responseCode = "404", description = "Glasses not found")
    public GlassesResponseDto updateGlasses(@PathVariable Long id,
                                            @RequestBody GlassesRequestDto glassesRequestDto) {
        logger.info("Updating glasses with ID: " + id);
        return glassesService.update(id, glassesRequestDto);
    }

    @Operation(summary = "Search for glass",
            description = "Searches for glass in the store based on various search parameters"
                    + " such as color, manufacturer, or model.")
    @GetMapping("/search")
    @ApiResponse(responseCode = "200", description = "Glasses found successfully")
    public List<GlassesResponseDto> searchGlasses(GlassesSearchParameter searchParameters) {
        return glassesService.searchGlassesByParameters(searchParameters);
    }

    @Operation(summary = "Search for glasses",
            description = "Searches for glasses in the store based on "
                    + "various search parameters such as color, manufacturer or model.")
    @GetMapping("/search-similiar")
    @ApiResponse(responseCode = "200", description = "Glasses found successfully")
    public List<GlassesResponseDto> searchLiquidsWithSimiliarParams(
            GlassesSearchParameter searchParameters) {
        List<GlassesResponseDto> foundGlasses = glassesService
                .searchGlassesByParameters(searchParameters);
        List<GlassesResponseDto> similarGlasses = glassesService
                .findSimilar(searchParameters);
        Set<GlassesResponseDto> combinedSet = new HashSet<>(foundGlasses);
        combinedSet.addAll(similarGlasses);
        return new ArrayList<>(combinedSet);
    }

    @Operation(summary = "Delete glasses by their ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "204", description = "Glasses deleted successfully")
    @ApiResponse(responseCode = "404", description = "Glasses not found")
    public void deleteGlasses(@PathVariable Long id) {
        logger.info("Deleting glasses with ID: " + id);
        glassesService.deleteById(id);
    }
}
