package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.AttributeDicDto;

import java.util.List;

public interface AttributeDicRepository {

    List<AttributeDicDto> findAll();

}
