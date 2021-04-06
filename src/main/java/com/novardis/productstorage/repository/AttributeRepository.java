package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.ProductAttributeViewDto;

import java.util.Collection;
import java.util.List;

public interface AttributeRepository {

    List<ProductAttributeViewDto> findAllByProductId(Long id);

    List<ProductAttributeViewDto> findAllByProductIdIn(Collection<Long> ids);

    List<ProductAttributeViewDto> findAllByAttributeName(String attributeName);

    List<ProductAttributeViewDto> findAll();

}
