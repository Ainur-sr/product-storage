package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.ProductAttributeViewDto;

import java.util.List;

public interface AttributeRepository {

    List<ProductAttributeViewDto> findAllByProductId(Long id);
    List<ProductAttributeViewDto> findAll();

}
