package com.novardis.productstorage.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "id")
public class IndexPK {

    @ApiModelProperty(value = "id")
    private Long id;

}
