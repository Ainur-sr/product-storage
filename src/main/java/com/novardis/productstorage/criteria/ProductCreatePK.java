package com.novardis.productstorage.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "Параметры создания продукта")
public class ProductCreatePK {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 1024, message = "Name should be between 1 and 1024 characters")
    @ApiModelProperty(value = "Наименование")
    private String name;

}
