package com.novardis.productstorage.rest;

import com.novardis.productstorage.criteria.ProductAttributePK;
import com.novardis.productstorage.criteria.ProductAttributeDeletePK;
import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.service.ProductAttributeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttributeController {

    private final ProductAttributeService productAttributeService;

    @ApiOperation("Добавить аттрибут к товару")
    @PostMapping("/attribute/add")
    public Product createProductAttribute(@RequestBody @Valid ProductAttributePK param){
        return productAttributeService.addProductAttribute(param);
    }

    @ApiOperation("Обновить аттрибут у товара")
    @PostMapping("/attribute/update")
    public Product updateProductAttribute(@RequestBody @Valid ProductAttributePK param){
        return productAttributeService.updateProductAttribute(param);
    }

    @ApiOperation("Удалить аттрибут товара по id")
    @PostMapping("/attribute/delete")
    public Product deleteProductAttribute(@RequestBody @Valid ProductAttributeDeletePK param) {
        return productAttributeService.deleteProductAttribute(param);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
