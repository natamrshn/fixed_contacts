package spring.boot.optic.okulist.specification.glasses.providers;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.model.Glasses;

public class ModelSpecificationProviderGlasses implements SpecificationProviderGlasses<Glasses> {
    @Override
    public String getKey() {
        return "model";
    }

    @Override
    public Specification<Glasses> getGlassesSpecification(String[] params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Invalid number of parameters "
                    + "for model specification");
        }

        String model = params[0];

        return (root, query, builder) -> builder.equal(root.get("model"), model);
    }
}
