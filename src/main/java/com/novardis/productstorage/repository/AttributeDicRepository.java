package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.AttributeDicDto;

import java.util.List;
import java.util.Optional;

public interface AttributeDicRepository {

    List<AttributeDicDto> findAll();
    Optional<AttributeDicDto> findById(Long id);

}
