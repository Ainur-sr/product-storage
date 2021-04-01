package com.novardis.productstorage.service;

import com.novardis.productstorage.domain.DictionaryAttribute;

import java.util.List;

public interface DictionaryAttributeService {
    List<DictionaryAttribute> getAttributeDicByDicId(Long dicId);
}
