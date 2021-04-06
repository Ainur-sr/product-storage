package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Long save(ProductDto productDto);

    boolean update(ProductDto productDto);

    boolean deleteById(Long id);

    List<ProductDto> findAll();

    List<ProductDto> findAllByName(String productName);

    Optional<ProductDto> findById(Long id);
}
