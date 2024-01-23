package spring.boot.optic.okulist.mapper.contactlenses;

import static java.lang.Double.parseDouble;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.model.Category;
import spring.boot.optic.okulist.model.lenses.ContactLenses;
import spring.boot.optic.okulist.model.lenses.parameters.Color;
import spring.boot.optic.okulist.model.lenses.parameters.Cylinder;
import spring.boot.optic.okulist.model.lenses.parameters.Degree;
import spring.boot.optic.okulist.model.lenses.parameters.Diopter;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;
import spring.boot.optic.okulist.model.lenses.parameters.Sphere;

@Mapper(config = MapperConfig.class)
public interface ContactLensesMapper {

    @Mapping(target = "categories", ignore = true)
    ContactLensesResponseDto toDto(ContactLenses contactLenses);

    ContactLenses toModel(ContactLensesRequestDto contactLensesRequestDto);

    @AfterMapping
    default void mapLensesDetails(@MappingTarget ContactLensesResponseDto contactLensesResponseDto,
                                  ContactLenses contactLenses) {
        contactLensesResponseDto.setName(contactLenses.getName());

        Manufacturer lensConfig = contactLenses.getLensConfiguration();

        List<Color> colors = lensConfig.getColors();
        if (colors != null) {
            contactLensesResponseDto.setColors(colors.stream()
                    .map(Color::getColor)
                    .toList());
        }

        Cylinder cylinder = lensConfig.getCylinder();
        if (cylinder != null) {
            contactLensesResponseDto.setCylinders(cylinder.getRangeAsList());
        }

        Degree degree = lensConfig.getDegree();
        if (degree != null) {
            contactLensesResponseDto.setDegrees(degree.getRangeAsList()
                    .stream()
                    .mapToInt(Double::intValue)
                    .boxed()
                    .toList());
        }
        Diopter diopter = lensConfig.getDiopter();
        if (diopter != null) {
            contactLensesResponseDto.setDiopters(diopter.getRangeAsList());
        }
        List<Sphere> spheres = lensConfig.getSpheres();
        if (spheres != null) {
            contactLensesResponseDto.setSpheres(spheres.stream()
                    .map(value -> parseDouble(value.getBaseCurve().replaceAll("\\+", "")))
                    .toList());
        }
        contactLensesResponseDto.setCategories(mapCategoriesToDto(contactLenses
                .getCategories()));
    }

    default Set<CategoryResponseDto> mapCategoriesToDto(Set<Category> categories) {
        return categories.stream()
                .map(category -> {
                    CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                    categoryResponseDto.setId(category.getId());
                    categoryResponseDto.setName(category.getName());
                    return categoryResponseDto;
                })
                .collect(Collectors.toSet());
    }
}

