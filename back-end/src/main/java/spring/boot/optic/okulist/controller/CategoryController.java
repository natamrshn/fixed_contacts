package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import spring.boot.optic.okulist.dto.category.CategoryRequestDto;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.service.category.CategoryService;

@Tag(name = "Category Controller",
        description = "Endpoints for managing categories")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    private static final Logger logger = LogManager.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    @Operation(summary = "Create a Category",
            description = "Creates a new category for books in the library.")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        logger.info("Creating a new category.");
        return categoryService.save(categoryRequestDto);
    }

    @Operation(summary = "Get All categories",
            description = "Retrieves a list of all categories available for books.")
    @GetMapping
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @Operation(summary = "Get Category by ID", description = "Retrieves a category by its unique identifier (ID).")
    @ApiResponse(responseCode = "200", description = "Category retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @Parameter(name = "id", description = "ID of the category to be retrieved", required = true)
    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        logger.info("Retrieving category with ID: " + id);
        return categoryService.getById(id);
    }

    @Operation(summary = "Update a Category by its ID",
            description = "Updates an existing category in the library based on its unique identifier (ID).")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody CategoryRequestDto categoryRequestDto) {
        logger.info("Updating category with ID: " + id);
        return categoryService.update(id, categoryRequestDto);
    }

    @Operation(summary = "Delete category by their ID",
            description = "Soft deletes a category from the library by its unique identifier (ID).")
    @ApiResponse(responseCode = "204", description = "Category deleted successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        logger.info("Deleting category with ID: " + id);
        categoryService.deleteById(id);
    }
}
