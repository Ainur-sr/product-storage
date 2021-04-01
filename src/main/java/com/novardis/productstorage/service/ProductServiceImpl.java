package com.novardis.productstorage.service;

import com.novardis.productstorage.domain.Attribute;
import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.dto.AttributeDto;
import com.novardis.productstorage.dto.ProductDto;
import com.novardis.productstorage.mapper.AttributeMapper;
import com.novardis.productstorage.mapper.ProductMapper;
import com.novardis.productstorage.repository.AttributeRepository;
import com.novardis.productstorage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AttributeRepository attributeRepository;

    private final ProductMapper productMapper;
    private final AttributeMapper attributeMapper;

    @Override
    public List<Product> getAll() {
        List<Product> products = null;
        List<ProductDto> productDtoList = productRepository.findAll();
        if (!CollectionUtils.isEmpty(productDtoList)) {
            products = productDtoList.stream()
                    .map(productMapper::toDomain)
                    .collect(Collectors.toList());
            List<AttributeDto> attributeDtoList = attributeRepository.findAll();
            if (!CollectionUtils.isEmpty(productDtoList)) {
                Map<Long, List<AttributeDto>> attributeDtoMap = attributeDtoList.stream().collect(Collectors.groupingBy(AttributeDto::getProductId));
                products.forEach(product -> {
                    if (!CollectionUtils.isEmpty(attributeDtoMap.get(product.getId())))
                    product.setAttributes(attributeDtoMap.get(product.getId()).stream().map(attributeMapper::toDomain).collect(Collectors.toList()));
                });
            }
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
        Product product = productMapper.toDomain(productRepository.findById(id).orElse(null));
        if (product != null) {
            List<AttributeDto> attributeDtoList = attributeRepository.findAllByProductId(id);
            if (!CollectionUtils.isEmpty(attributeDtoList)) {
                List<Attribute> attributes = attributeDtoList.stream().map(attributeMapper::toDomain).collect(Collectors.toList());
                product.setAttributes(attributes);
            }
        }
        return product;
    }

}
