package com.novardis.productstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AttributeOneDto {

    private Long id;
    private Long attributeOneDicId;
    private String name;
    private String description;
    private String unit;
    private String value;

}
