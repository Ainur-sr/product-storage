package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.DictionaryAttributeValDto;

import java.util.List;
import java.util.Optional;

public interface DictionaryAttributeValRepository {

    Long save(String attributeValTableName, String value, Long attributeDicId);

//    Long save(DictionaryAttributeValDto dictionaryAttributeValDto);

//    boolean update(DictionaryAttributeValDto dictionaryAttributeValDto);

//    boolean deleteById(Long id);


//    Optional<DictionaryAttributeValDto> findById(Long id);

}
