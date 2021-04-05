package com.novardis.productstorage.service;

import com.novardis.productstorage.domain.DictionaryAttribute;

import java.util.List;

public interface DictionaryAttributeService {
    List<DictionaryAttribute> getAllAttributeDicByDicId(Long dicId);
    DictionaryAttribute getAttributeByDicIdAndId(Long dicId, Long attributeId);

}
