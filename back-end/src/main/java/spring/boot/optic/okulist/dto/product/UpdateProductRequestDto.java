package spring.boot.optic.okulist.dto.product;

import lombok.Data;
import spring.boot.optic.okulist.model.Product;

@Data
public class UpdateProductRequestDto {
    private Product.ProductStatus productStatus;
}
