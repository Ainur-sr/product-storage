package com.novardis.productstorage.repository;


import com.novardis.productstorage.dto.DictionaryAttributeValDto;

import java.util.Optional;

public interface DictionaryAttributeValRepository {

    Long save(String attributeValTableName, String value, Long attributeDicId, Long attributeLinkId, Long productId);

    Optional<DictionaryAttributeValDto> findByProductId(String attributeValTableName, Long productId);

//    boolean update(DictionaryAttributeValDto dictionaryAttributeValDto);

//    boolean deleteById(Long id);

}
