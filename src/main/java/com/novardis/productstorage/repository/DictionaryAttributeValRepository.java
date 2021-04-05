package com.novardis.productstorage.repository;


import com.novardis.productstorage.dto.DictionaryAttributeValDto;

import java.util.Optional;

public interface DictionaryAttributeValRepository {

    Long save(String attributeValTableName, String value, Long attributeDicId, Long attributeLinkId, Long productId);

    Optional<DictionaryAttributeValDto> findByProductIdAndAttributeDicId(String attributeValTableName, Long productId, Long attributeDicId);

    boolean updateValue(String attributeValTableName, String value, Long id);

}
