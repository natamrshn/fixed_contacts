package spring.boot.optic.okulist.specification.glasses.providers;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.model.Glasses;

public class ColorSpecificationProviderGlasses implements SpecificationProviderGlasses<Glasses> {
    @Override
    public String getKey() {
        return "color";
    }

    @Override
    public Specification<Glasses> getGlassesSpecification(String[] params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Invalid number of "
                    + "parameters for color specification");
        }

        String color = params[0];

        return (root, query, builder) -> builder.equal(root.get("color"), color);
    }
}
