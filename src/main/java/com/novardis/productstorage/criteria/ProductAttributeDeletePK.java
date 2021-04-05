package com.novardis.productstorage.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Параметры удаления аттрибута у продукта")
public class ProductAttributeDeletePK {

    @NotNull(message = "Must not be null")
    @ApiModelProperty(value = "id продукта")
    private Long productId;

    @NotNull(message = "Must not be null")
    @ApiModelProperty(value = "id словаря атрибутов")
    private Long dicId;

    @NotNull(message = "Must not be null")
    @ApiModelProperty(value = "id атрибута")
    private Long attributeId;
}
