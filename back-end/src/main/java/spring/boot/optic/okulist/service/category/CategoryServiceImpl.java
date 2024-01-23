package spring.boot.optic.okulist.service.category;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.category.CategoryRequestDto;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.CategoryMapper;
import spring.boot.optic.okulist.model.Category;
import spring.boot.optic.okulist.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category newCategory = categoryMapper.toEntity(categoryRequestDto);
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Category not found with id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        Category savedCategory = categoryRepository.save(categoryMapper
                .toEntity(categoryRequestDto));
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryResponseDto update(Long id,
                                      CategoryRequestDto categoryRequestDto) {
        Category categoryById = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find category by id: " + id));
        Category update = categoryMapper.toEntity(categoryRequestDto);
        update.setId(id);
        return categoryMapper.toDto(categoryRepository.save(update));
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found by ID :" + id));
        categoryRepository.delete(category);
    }
}
