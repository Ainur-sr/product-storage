package com.novardis.productstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDto {

    private Long productId;
    private String productName;
    private Long dicId;
    private Long attributeId;
    private String attributeName;
    private String attributeDescription;
    private String attributeUnit;
    private String attributeValue;

}
