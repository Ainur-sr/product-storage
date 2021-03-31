package com.novardis.productstorage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "Товар")
public class Product {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Наименование")
    private String name;

}
