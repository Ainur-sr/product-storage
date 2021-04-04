package com.novardis.productstorage.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Параметры обновления продукта")
public class ProductUpdatePK {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Наименование")
    private String name;

}
