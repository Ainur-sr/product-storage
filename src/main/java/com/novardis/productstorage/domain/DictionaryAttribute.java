package com.novardis.productstorage.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "Атрибут (словарь)")
public class DictionaryAttribute {

    private Long id;

    private String name;

    private String description;

    private String unit;
}
