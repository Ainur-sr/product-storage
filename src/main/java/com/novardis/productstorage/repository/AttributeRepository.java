package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.AttributeDto;

import java.util.List;

public interface AttributeRepository {

    List<AttributeDto> findAllByProductId(Long id);
    List<AttributeDto> findAll();

}
