package com.novardis.productstorage.repository;

import com.novardis.productstorage.dto.DictionaryAttributeValDto;

import java.util.List;
import java.util.Optional;

public interface DictionaryAttributeValRepository {

    Long save(DictionaryAttributeValDto dictionaryAttributeValDto);

    boolean update(DictionaryAttributeValDto dictionaryAttributeValDto);

    boolean deleteById(Long id);

    List<DictionaryAttributeValDto> findAll();

    Optional<DictionaryAttributeValDto> findById(Long id);

}
