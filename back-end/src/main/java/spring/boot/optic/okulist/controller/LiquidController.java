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
import spring.boot.optic.okulist.dto.liquid.LiquidRequestDto;
import spring.boot.optic.okulist.dto.liquid.LiquidResponseDto;
import spring.boot.optic.okulist.dto.liquid.LiquidSearchParameter;
import spring.boot.optic.okulist.service.liquid.LiquidService;

@Tag(name = "Liquid Controller",
        description = "Endpoints for managing liquids")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/liquid")
public class LiquidController {
    private static final Logger logger = LogManager.getLogger(LiquidController.class);
    private final LiquidService liquidService;

    @Operation(summary = "Create a Liquid product ",
            description = "Creates a new liquid in shop list.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Liquid created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided.")
    public LiquidResponseDto createLiquids(@RequestBody
                                           @Valid LiquidRequestDto liquidRequestDto) {
        logger.info("Creating new liquid product");
        return liquidService.save(liquidRequestDto);
    }

    @Operation(summary = "Update Liquid by ID")
    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Liquid updated successfully")
    @ApiResponse(responseCode = "404", description = "Liquid not found")
    public LiquidResponseDto updateGlasses(@PathVariable Long id,
                                           @RequestBody LiquidRequestDto liquidRequestDto) {
        logger.info("Updating liquid with ID: " + id);
        return liquidService.update(id, liquidRequestDto);
    }

    @Operation(summary = "Get All liquids ")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of liquids retrieved successfully")
    public List<LiquidResponseDto> getAllLiquids(Pageable pageable) {
        return liquidService.findAll(pageable);
    }

    @Operation(summary = "Get liquid by ID")
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Liquid retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Liquid not found")
    public LiquidResponseDto getLiquidsById(@PathVariable Long id) {
        logger.info("Retrieving liquid with ID: " + id);
        return liquidService.getById(id);
    }

    @Operation(summary = "Search for liquid",
            description = "Searches for liquid in the store based on various search parameters such as volume or name.")
    @GetMapping("/search")
    @ApiResponse(responseCode = "200", description = "Liquid found successfully")
    public List<LiquidResponseDto> searchLiquids(LiquidSearchParameter searchParameters) {
        return liquidService.searchLiquidByParameters(searchParameters);
    }

    @Operation(summary = "Search for liquid",
            description = "Searches for liquid in the store based on various search parameters such as volume or name.")
    @GetMapping("/search-similiar")
    @ApiResponse(responseCode = "200", description = "Liquid found successfully")
    public List<LiquidResponseDto> searchLiquidsWithSimilarParams(
            LiquidSearchParameter searchParameters) {
        List<LiquidResponseDto> foundLiquids = liquidService
                .searchLiquidByParameters(searchParameters);
        List<LiquidResponseDto> similarLiquids = liquidService
                .findSimilar(searchParameters);
        Set<LiquidResponseDto> combinedSet = new HashSet<>(foundLiquids);
        combinedSet.addAll(similarLiquids);
        return new ArrayList<>(combinedSet);
    }

    @Operation(summary = "Delete liquids by their ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "204", description = "Liquid deleted successfully")
    @ApiResponse(responseCode = "404", description = "Liquid not found")
    public void deleteLiquids(@PathVariable Long id) {
        logger.info("Deleting liquid with ID: " + id);
        liquidService.deleteById(id);
    }
}
