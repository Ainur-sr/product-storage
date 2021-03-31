package com.novardis.productstorage.service;

import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.dto.ProductDto;
import com.novardis.productstorage.mapper.ProductMapper;
import com.novardis.productstorage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> getAll() {
        List<Product> products = null;
        List<ProductDto> productDtoList = productRepository.findAll();
        if (!CollectionUtils.isEmpty(productDtoList)) {
            products = productDtoList.stream()
                    .map(productMapper::toDomain)
                    .collect(Collectors.toList());
        } else {
            products = Collections.emptyList();
        }

        return products;
    }

    @Override
    public Product createProduct(Product product) {
        Long productId = productRepository.save(productMapper.toDto(product));
        if (productId != null) {
            ProductDto productDto = productRepository.findById(productId).orElse(null);
            return productMapper.toDomain(productDto);
        } else {
            return null;
        }
    }

    @Override
    public Product updateProduct(Product product) {
        Product result = null;
        if (product != null) {
            boolean isUpdate = productRepository.update(productMapper.toDto(product));
            if (isUpdate) {
                result = productMapper.toDomain(productRepository.findById(product.getId()).orElse(null));
            }
        }
        return result;
    }

    @Override
    public boolean deleteProductById(Long id) {
        return productRepository.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        return productMapper.toDomain(productRepository.findById(id).orElse(null));
    }

}
