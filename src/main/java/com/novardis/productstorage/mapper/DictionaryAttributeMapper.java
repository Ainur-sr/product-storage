package com.novardis.productstorage.mapper;

import com.novardis.productstorage.domain.DictionaryAttribute;
import com.novardis.productstorage.dto.DictionaryAttributeDto;
import org.springframework.stereotype.Component;

@Component
public class DictionaryAttributeMapper {

    public DictionaryAttribute toDomain(DictionaryAttributeDto dto){
        DictionaryAttribute dictionaryAttribute = new DictionaryAttribute();
        if (dto != null){
            dictionaryAttribute
                    .setId(dto.getId())
                    .setName(dto.getName())
                    .setDescription(dto.getDescription())
                    .setUnit(dto.getUnit());
        }
        return dictionaryAttribute;
    }

}
