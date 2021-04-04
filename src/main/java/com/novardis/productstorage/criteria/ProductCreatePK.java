package com.novardis.productstorage.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Параметры создания продукта")
public class ProductCreatePK {

    @ApiModelProperty(value = "Наименование")
    private String name;

}
