package com.novardis.productstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DictionaryAttributeValDto {
    private Long attributeId;
    private String attributeValue;
    private Long attributeDicId;
    private Long attributeLinkId;
    private Long productId;
}
