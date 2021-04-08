package com.novardis.productstorage.service;

import com.novardis.productstorage.criteria.ProductCreatePK;
import com.novardis.productstorage.criteria.ProductUpdatePK;
import com.novardis.productstorage.domain.Attribute;
import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.dto.ProductAttributeViewDto;
import com.novardis.productstorage.dto.ProductDto;
import com.novardis.productstorage.mapper.AttributeMapper;
import com.novardis.productstorage.mapper.ProductMapper;
import com.novardis.productstorage.repository.AttributeLinkRepository;
import com.novardis.productstorage.repository.AttributeRepository;
import com.novardis.productstorage.repository.ProductAttributeLinkRepository;
import com.novardis.productstorage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeLinkRepository attributeLinkRepository;

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
            List<ProductAttributeViewDto> productAttributeViewDtoList = attributeRepository.findAll();
            if (!CollectionUtils.isEmpty(productAttributeViewDtoList)) {
                Map<Long, List<ProductAttributeViewDto>> attributeDtoMap = productAttributeViewDtoList.stream().collect(Collectors.groupingBy(ProductAttributeViewDto::getProductId));
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

    @Transactional
    @Override
    public Product createProduct(ProductCreatePK productCreatePK) {
        Product product = new Product();
        product.setName(productCreatePK.getName());
        Long productId = productRepository.save(productMapper.toDto(product));
        if (productId != null) {
            ProductDto productDto = productRepository.findById(productId).orElse(null);
            return productMapper.toDomain(productDto);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public Product updateProduct(ProductUpdatePK productUpdatePK) {
        Product result = null;
        if (productUpdatePK != null) {
            Product product = new Product();
            product
                    .setId(productUpdatePK.getId())
                    .setName(productUpdatePK.getName());

            boolean isUpdate = productRepository.update(productMapper.toDto(product));
            if (isUpdate) {
                result = productMapper.toDomain(productRepository.findById(product.getId()).orElse(null));
                List<ProductAttributeViewDto> productAttributeViewDtoList = attributeRepository.findAllByProductId(result.getId());
                if (!CollectionUtils.isEmpty(productAttributeViewDtoList)) {
                    List<Attribute> attributes = productAttributeViewDtoList.stream().map(attributeMapper::toDomain).collect(Collectors.toList());
                    result.setAttributes(attributes);
                }
            }
        }
        return result;
    }

    @Transactional
    @Override
    public boolean deleteProductById(Long productId) {
        attributeLinkRepository.deleteByProductId(productId);
        return productRepository.deleteById(productId);
    }

    @Override
    public Product getById(Long productId) {
        Product product = productMapper.toDomain(productRepository.findById(productId).orElse(null));
        if (product != null) {
            List<ProductAttributeViewDto> productAttributeViewDtoList = attributeRepository.findAllByProductId(productId);
            if (!CollectionUtils.isEmpty(productAttributeViewDtoList)) {
                List<Attribute> attributes = productAttributeViewDtoList.stream().map(attributeMapper::toDomain).collect(Collectors.toList());
                product.setAttributes(attributes);
            }
        }
        return product;
    }

    @Override
    public List<Product> getAllByProductName(String productName) {
        List<Product> products = null;
        List<ProductDto> productDtoList = productRepository.findAllByName(productName);
        if (!CollectionUtils.isEmpty(productDtoList)) {
            products = productDtoList.stream()
                    .map(productMapper::toDomain)
                    .collect(Collectors.toList());
            Set<Long> productIdSet = products.stream().map(Product::getId).collect(Collectors.toSet());
            List<ProductAttributeViewDto> productAttributeViewDtoList = attributeRepository.findAllByProductIdIn(productIdSet);
            if (!CollectionUtils.isEmpty(productAttributeViewDtoList)) {
                Map<Long, List<ProductAttributeViewDto>> attributeDtoMap = productAttributeViewDtoList.stream().collect(Collectors.groupingBy(ProductAttributeViewDto::getProductId));
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
    public List<Product> getAllByAttributeName(String attributeName) {
        List<Product> products = null;
        List<ProductAttributeViewDto> productAttributeViewDtoList = attributeRepository.findAllByAttributeName(attributeName);
        if (!CollectionUtils.isEmpty(productAttributeViewDtoList)) {
            Set<Long> productIdSet = productAttributeViewDtoList.stream().map(ProductAttributeViewDto::getProductId).collect(Collectors.toSet());
            List<ProductDto> productDtoList = productRepository.findAllByIdIn(productIdSet);
            if (!CollectionUtils.isEmpty(productDtoList)) {
                Map<Long, List<ProductAttributeViewDto>> attributeDtoMap = productAttributeViewDtoList.stream().collect(Collectors.groupingBy(ProductAttributeViewDto::getProductId));

                products = productDtoList.stream()
                        .map(productMapper::toDomain)
                        .collect(Collectors.toList());

                products.forEach(product -> {
                    if (!CollectionUtils.isEmpty(attributeDtoMap.get(product.getId())))
                        product.setAttributes(attributeDtoMap.get(product.getId()).stream().map(attributeMapper::toDomain).collect(Collectors.toList()));
                });

             } else {
                products = Collections.emptyList();
            }
        } else {
            products = Collections.emptyList();
        }

        return products;
    }

}
