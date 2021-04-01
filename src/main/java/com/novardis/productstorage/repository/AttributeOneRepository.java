package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.AttributeOneDto;

import java.util.List;
import java.util.Optional;

public interface AttributeOneRepository {

    Long save(AttributeOneDto attributeOneDto);

    boolean update(AttributeOneDto attributeOneDto);

    boolean deleteById(Long id);

    List<AttributeOneDto> findAll();

    Optional<AttributeOneDto> findById(Long id);

}
