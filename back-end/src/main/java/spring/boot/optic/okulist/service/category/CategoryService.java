package spring.boot.optic.okulist.service.category;

import java.util.List;
import org.springframework.data.domain.Pageable;
import spring.boot.optic.okulist.dto.category.CategoryRequestDto;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto);

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    void deleteById(Long id);
}
