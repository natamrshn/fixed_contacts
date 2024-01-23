package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereResponseDto;
import spring.boot.optic.okulist.service.contactlenses.params.color.ColorService;
import spring.boot.optic.okulist.service.contactlenses.params.cylinder.CylinderService;
import spring.boot.optic.okulist.service.contactlenses.params.degree.DegreeService;
import spring.boot.optic.okulist.service.contactlenses.params.diopter.DiopterService;
import spring.boot.optic.okulist.service.contactlenses.params.sphere.SphereService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/parameters")
public class ParametersController {
    private final ColorService colorService;
    private final CylinderService cylinderService;
    private final DegreeService degreeService;
    private final DiopterService diopterService;
    private final SphereService sphereService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create-color")
    @Operation(summary = "Create color", description = "Create a new color")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Color created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<ColorResponseDto> createColor(@RequestBody
                                                        @Valid ColorRequestDto colorRequestDto) {
        ColorResponseDto createdColor = colorService.createColor(colorRequestDto);
        return new ResponseEntity<>(createdColor, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Colors")
    @Operation(summary = "Get all colors", description = "Get a list of all available colors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colors retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<List<ColorResponseDto>> getAllColors() {
        List<ColorResponseDto> allColors = colorService.getAllColors();
        return ResponseEntity.ok(allColors);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/colors/{id}")
    @Operation(summary = "Get color by ID", description = "Get available color by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Color retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required"),
            @ApiResponse(responseCode = "404", description = "Color not found")
    })
    public ColorResponseDto getColorById(@PathVariable Long id) {
        return colorService.getColorById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/cylinders")
    @Operation(summary = "Create cylinder", description = "Create a new cylinder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cylinder created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<CylinderResponseDto> createCylinder(
            @RequestBody @Valid CylinderRequestDto cylinderRequestDto) {
        CylinderResponseDto createdCylinder = cylinderService.createCylinder(cylinderRequestDto);
        return new ResponseEntity<>(createdCylinder, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Cylinders")
    @Operation(summary = "Get all cylinders", description = "Get a list of all available cylinders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cylinders retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<List<CylinderResponseDto>> getAllCylinders() {
        List<CylinderResponseDto> allCylinders = cylinderService.getAllCylinders();
        return ResponseEntity.ok(allCylinders);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cylinder/{id}")
    @Operation(summary = "Get cylinder by ID", description = "Get available cylinder by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cylinder retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required"),
            @ApiResponse(responseCode = "404", description = "Cylinder not found")
    })
    public CylinderResponseDto getCylinderById(@PathVariable Long id) {
        return cylinderService.getCylinderById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/degrees")
    @Operation(summary = "Create degree", description = "Create a new degree")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Degree created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<DegreeResponseDto> createDegree(
            @RequestBody @Valid DegreeRequestDto degreeRequestDto) {
        DegreeResponseDto createdDegree = degreeService.createDegree(degreeRequestDto);
        return new ResponseEntity<>(createdDegree, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Degrees")
    @Operation(summary = "Get all degrees", description = "Get a list of all available degrees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Degrees retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<List<DegreeResponseDto>> getAllDegrees() {
        List<DegreeResponseDto> allDegrees = degreeService.getAllDegrees();
        return ResponseEntity.ok(allDegrees);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/degrees/{id}")
    @Operation(summary = "Get degree by ID", description = "Get available degree by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Degree retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required"),
            @ApiResponse(responseCode = "404", description = "Degree not found")
    })
    public DegreeResponseDto getDegreeById(@PathVariable Long id) {
        return degreeService.getDegreeById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/diopters")
    @Operation(summary = "Create diopter", description = "Create a new diopter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Diopter created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<DiopterResponseDto> createDiopter(
            @RequestBody @Valid DiopterRequestDto diopterRequestDto) {
        DiopterResponseDto createdDiopter = diopterService.createDiopter(diopterRequestDto);
        return new ResponseEntity<>(createdDiopter, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Diopters")
    @Operation(summary = "Get all diopters", description = "Get a list of all available diopters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diopters retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<List<DiopterResponseDto>> getAllDiopters() {
        List<DiopterResponseDto> allDiopters = diopterService.getAllDiopters();
        return ResponseEntity.ok(allDiopters);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/diopters/{id}")
    @Operation(summary = "Get diopter by ID", description = "Get available diopter by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diopter retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required"),
            @ApiResponse(responseCode = "404", description = "Diopter not found")
    })
    public DiopterResponseDto getDiopterById(@PathVariable Long id) {
        return diopterService.getDiopterById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/spheres")
    @Operation(summary = "Create sphere", description = "Create a new sphere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sphere created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<SphereResponseDto> createSphere(
            @RequestBody @Valid SphereRequestDto sphereRequestDto) {
        SphereResponseDto createdSphere = sphereService.createSphere(sphereRequestDto);
        return new ResponseEntity<>(createdSphere, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Spheres")
    @Operation(summary = "Get all spheres", description = "Get a list of all available spheres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spheres retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required")
    })
    public ResponseEntity<List<SphereResponseDto>> getAllSpheres() {
        List<SphereResponseDto> allSpheres = sphereService.getAllSpheres();
        return ResponseEntity.ok(allSpheres);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/spheres/{id}")
    @Operation(summary = "Get sphere by ID", description = "Get available sphere by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sphere retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required"),
            @ApiResponse(responseCode = "404", description = "Sphere not found")
    })
    public SphereResponseDto getSphereById(@PathVariable Long id) {
        return sphereService.getSphereById(id);
    }
}
