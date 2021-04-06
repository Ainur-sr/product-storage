package com.novardis.productstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DictionaryAttributeDto {

    private Long id;
    private String name;
    private String description;
    private String unit;
}
