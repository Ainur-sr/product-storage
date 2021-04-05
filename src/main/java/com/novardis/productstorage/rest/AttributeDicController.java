package com.novardis.productstorage.rest;

import com.novardis.productstorage.domain.AttributeDic;
import com.novardis.productstorage.domain.DictionaryAttribute;
import com.novardis.productstorage.service.AttributeDicService;
import com.novardis.productstorage.service.DictionaryAttributeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttributeDicController {

    private final AttributeDicService attributeDicService;
    private final DictionaryAttributeService dictionaryAttributeService;

    @ApiOperation("Получить список справочных таблиц для атрибутов товара")
    @GetMapping("/attributedic/all")
    public List<AttributeDic> getAttributeDicList(){
        return attributeDicService.getAll();
    }

    @ApiOperation("Получить содержимое справочной таблицы по id")
    @GetMapping("/attributedic/content/{id}")
    public List<DictionaryAttribute> getAttributeDic(@PathVariable("id") Long id){
        List<DictionaryAttribute> resultList = null;
        if (id != null){
            resultList = dictionaryAttributeService.getAttributeDicByDicId(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameter 'id' is null");
        }
        return resultList;
    }

}
