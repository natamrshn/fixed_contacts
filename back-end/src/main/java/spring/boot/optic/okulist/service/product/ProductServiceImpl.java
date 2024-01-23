package spring.boot.optic.okulist.service.product;

import static spring.boot.optic.okulist.service.liquid.LiquidServiceImpl.getStrings;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.product.ProductRequestDto;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.dto.product.ProductSearchParameter;
import spring.boot.optic.okulist.dto.product.UpdateProductRequestDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.ProductMapper;
import spring.boot.optic.okulist.model.Product;
import spring.boot.optic.okulist.repository.ProductRepository;
import spring.boot.optic.okulist.specification.product.builders.ProductSpecificationBuilder;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductSpecificationBuilder productSpecificationBuilder;

    @Override
    public List<ProductResponseDto> searchProductByParameters(
            ProductSearchParameter searchParameters) {
        Specification<Product> productSpecification = productSpecificationBuilder
                .build(searchParameters);
        return productRepository.findAll(productSpecification)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found product with ID :" + id)
        );
        product.setDeleted(true);
        productRepository.save(product);
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find product with ID: " + id));
        BeanUtils.copyProperties(productRequestDto, existingProduct,
                getNullPropertyNames(productRequestDto));
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    private String[] getNullPropertyNames(Object source) {
        return getStrings(source);
    }

    @Override
    public ProductResponseDto updateProductStatus(Long id, UpdateProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find product by id: " + id));
        Product.ProductStatus newStatus = requestDto.getProductStatus();
        product.setStatus(newStatus);
        Product updatedProduct = productRepository.save(product);
        updatedProduct.setStatus(newStatus);
        return productMapper.toDto(updatedProduct);
    }
}
