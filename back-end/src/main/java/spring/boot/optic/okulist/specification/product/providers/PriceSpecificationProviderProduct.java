package spring.boot.optic.okulist.specification.product.providers;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.model.Product;

public class PriceSpecificationProviderProduct implements SpecificationProviderProduct<Product> {
    @Override
    public String getKey() {
        return "price";
    }

    @Override
    public Specification<Product> getProductSpecification(String[] params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Invalid number of "
                    + "parameters for price specification");
        }

        String price = params[0];

        return (root, query, builder) -> builder.equal(root.get("price"), price);
    }
}
