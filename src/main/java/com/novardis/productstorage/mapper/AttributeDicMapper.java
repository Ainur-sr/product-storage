package com.novardis.productstorage.mapper;

import com.novardis.productstorage.domain.AttributeDic;
import com.novardis.productstorage.dto.AttributeDicDto;
import org.springframework.stereotype.Component;

@Component
public class AttributeDicMapper {

    public AttributeDic toDomain(AttributeDicDto dto){
        AttributeDic attributeDic = new AttributeDic();
        if (dto != null){
            attributeDic
                    .setId(dto.getId())
                    .setName(dto.getName())
                    .setDescription(dto.getDescription());
        }
        return attributeDic;
    }

}
