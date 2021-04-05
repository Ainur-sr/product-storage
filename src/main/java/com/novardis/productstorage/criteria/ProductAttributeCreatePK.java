package com.novardis.productstorage.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "Параметры создания аттрибута к продукту")
public class ProductAttributeCreatePK {

    @NotNull(message = "Must not be null")
    @ApiModelProperty(value = "id продукта")
    private Long productId;

    @NotNull(message = "Must not be null")
    @ApiModelProperty(value = "id словаря атрибутов")
    private Long dicId;

    @NotNull(message = "Must not be null")
    @ApiModelProperty(value = "id атрибута")
    private Long attributeId;

    @NotBlank(message = "AttributeValue is mandatory")
    @Size(min = 1, max = 1024, message = "AttributeValue should be between 1 and 1024 characters")
    @ApiModelProperty(value = "Значение атрибута")
    private String attributeValue;

}
