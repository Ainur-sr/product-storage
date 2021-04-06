package com.novardis.productstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ProductAttributeLinkDto {

    private Long id;
    private Long attributeLinkId;
    private Long productId;
    private Long dicId;

}
