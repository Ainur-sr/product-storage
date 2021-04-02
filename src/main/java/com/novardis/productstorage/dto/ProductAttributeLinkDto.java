package com.novardis.productstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeLinkDto {

    private Long id;
    private Long attributeValueId;
    private Long productId;
    private Long attributeDicId;

}
