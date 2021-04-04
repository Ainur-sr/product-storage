package com.novardis.productstorage.rest;

import com.novardis.productstorage.criteria.ProductAttributePK;
import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.service.ProductAttributeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttributeController {

    private final ProductAttributeService productAttributeService;

    @ApiOperation("Добавить аттрибут к товару")
    @PostMapping("/attribute/add")
    public Product createProduct(@RequestBody ProductAttributePK productAttributePK){
        Product res = productAttributeService.addAttributeToProduct(productAttributePK);
        return res;
    }





}
