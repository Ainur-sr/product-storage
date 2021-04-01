package com.novardis.productstorage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "Товар")
public class Product {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Наименование")
    private String name;

    @ApiModelProperty(value = "Атрибуты")
    private List<Attribute> attributes = Collections.emptyList();

}
