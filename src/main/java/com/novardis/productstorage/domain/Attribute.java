package com.novardis.productstorage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "Атрибут товара")
public class Attribute {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Id справочной таблицы")
    private Long tableId;

    @ApiModelProperty(value = "Наименование")
    private String name;

    @ApiModelProperty(value = "Описание")
    private String description;

    @ApiModelProperty(value = "Значение")
    private String value;

    @ApiModelProperty(value = "Единица измерения")
    private String unit;

}
