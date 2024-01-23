package spring.boot.optic.okulist.specification.product.providers;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.model.Product;

public class CategoryIdsSpecificationProviderProduct
        implements SpecificationProviderProduct<Product> {
    @Override
    public String getKey() {
        return "category_id";
    }

    @Override
    public Specification<Product> getProductSpecification(String[] params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Invalid number of "
                    + "parameters for category id specification");
        }

        String categoryId = params[0];

        return (root, query, builder) -> builder.equal(root.get("category_id"), categoryId);
    }
}
