package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.DictionaryAttributeDto;

import java.util.List;
import java.util.Optional;

public interface DictionaryAttributeRepository {
    List<DictionaryAttributeDto> getAllByDicName(String dicTableName);
    Optional<DictionaryAttributeDto> getByDicNameAndId(String dicTableName, Long attributeId);
}
