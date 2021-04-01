package com.novardis.productstorage.rest;

import com.novardis.productstorage.domain.AttributeDic;
import com.novardis.productstorage.service.AttributeDicService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttributeDicController {

    private final AttributeDicService attributeDicService;

    @ApiOperation("Получить список справочных таблиц для атрибутов товара")
    @GetMapping("/attributedic/all")
    public List<AttributeDic> getAttributeDicList(){
        return attributeDicService.getAll();
    }



}
