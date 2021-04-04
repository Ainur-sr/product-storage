package com.novardis.productstorage.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Параметры аттрибута и продукта")
public class ProductAttributePK {

    @ApiModelProperty(value = "id продукта")
    private Long productId;

    @ApiModelProperty(value = "id словаря атрибутов")
    private Long dicId;

    @ApiModelProperty(value = "id атрибута")
    private Long attributeId;

    @ApiModelProperty(value = "Значение атрибута")
    private String attributeValue;

}
