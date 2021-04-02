package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.ProductAttributeLinkDto;

public interface ProductAttributeLinkRepository {

    Long save(ProductAttributeLinkDto productAttributeLinkDto);

}
