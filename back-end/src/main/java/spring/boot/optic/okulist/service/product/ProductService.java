package spring.boot.optic.okulist.service.product;

import java.util.List;
import spring.boot.optic.okulist.dto.product.ProductRequestDto;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.dto.product.ProductSearchParameter;
import spring.boot.optic.okulist.dto.product.UpdateProductRequestDto;

public interface ProductService {

    List<ProductResponseDto> searchProductByParameters(ProductSearchParameter searchParameters);

    void deleteById(Long id);

    ProductResponseDto update(Long id, ProductRequestDto productRequestDto);

    ProductResponseDto updateProductStatus(Long id, UpdateProductRequestDto requestDto);
}
