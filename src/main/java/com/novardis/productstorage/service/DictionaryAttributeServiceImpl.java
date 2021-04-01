package com.novardis.productstorage.service;

import com.novardis.productstorage.domain.DictionaryAttribute;
import com.novardis.productstorage.dto.AttributeDicDto;
import com.novardis.productstorage.dto.DictionaryAttributeDto;
import com.novardis.productstorage.mapper.DictionaryAttributeMapper;
import com.novardis.productstorage.repository.AttributeDicRepository;
import com.novardis.productstorage.repository.DictionaryAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DictionaryAttributeServiceImpl implements DictionaryAttributeService {

    private final AttributeDicRepository attributeDicRepository;
    private final DictionaryAttributeRepository dictionaryAttributeRepository;
    private final DictionaryAttributeMapper dictionaryAttributeMapper;

    @Override
    public List<DictionaryAttribute> getAttributeDicByDicId(Long dicId) {
        List<DictionaryAttribute> dictionaryAttributes = null;
        AttributeDicDto attributeDicDto = attributeDicRepository.findById(dicId).orElse(null);
        if (attributeDicDto != null){
            List<DictionaryAttributeDto> dictionaryAttributeDtoList = dictionaryAttributeRepository.getAllByDicName(attributeDicDto.getTableName());
            if (!CollectionUtils.isEmpty(dictionaryAttributeDtoList)){
                dictionaryAttributes = dictionaryAttributeDtoList.stream().map(dictionaryAttributeMapper::toDomain).collect(Collectors.toList());
            }
        }

        return dictionaryAttributes;
    }
}
