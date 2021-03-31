package com.novardis.productstorage.mapper;

import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductMapper {

    public Product toDomain(ProductDto dto) {
        Product product = new Product();
        if (dto != null) {
            product
                    .setId(dto.getId())
                    .setName(dto.getName());
        }
        return product;
    }

    public ProductDto toDto(Product product){
        ProductDto productDto = new ProductDto();
        if (product != null){
            productDto
                    .setId(product.getId())
                    .setName(product.getName());
        }
        return productDto;
    }

}
