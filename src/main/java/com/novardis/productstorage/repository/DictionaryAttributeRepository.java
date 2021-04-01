package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.DictionaryAttributeDto;

import java.util.List;

public interface DictionaryAttributeRepository {
    List<DictionaryAttributeDto> getAllByDicName(String dicTableName);
}
