package com.novardis.productstorage.mapper;

import com.novardis.productstorage.domain.Attribute;
import com.novardis.productstorage.dto.ProductAttributeViewDto;
import org.springframework.stereotype.Component;

@Component
public class AttributeMapper {

    public Attribute toDomain(ProductAttributeViewDto dto) {
        Attribute attribute = new Attribute();
        if (dto != null) {
            attribute
                    .setAttributeId(dto.getAttributeId())
                    .setDicId(dto.getDicId())
                    .setName(dto.getAttributeName())
                    .setDescription(dto.getAttributeDescription())
                    .setUnit(dto.getAttributeUnit())
                    .setValue(dto.getAttributeValue());
        }
        return attribute;
    }

}
