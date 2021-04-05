package com.novardis.productstorage.rest;

import com.novardis.productstorage.criteria.ProductCreatePK;
import com.novardis.productstorage.criteria.ProductUpdatePK;
import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @ApiOperation("Получить список товаров")
    @GetMapping("/product/all")
    public List<Product> getProducts(){
        return productService.getAll();
    }

    @ApiOperation("Создать товар")
    @PostMapping("/product")
    public Product createProduct(@RequestBody @Valid ProductCreatePK productCreatePK){
        Product res = productService.createProduct(productCreatePK);
        return res;
    }

    @ApiOperation("Обновить товар")
    @PutMapping("/product")
    public Product updateProduct(@RequestBody @Valid ProductUpdatePK productUpdatePK){
        return productService.updateProduct(productUpdatePK);
    }

    @ApiOperation("Удалить товар по id")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id) {
        ResponseEntity responseEntity = null;
        if (id != null) {
            boolean result = productService.deleteProductById(id);
            if (result){
                responseEntity = new ResponseEntity<>(HttpStatus.OK);
            } else {
               responseEntity = new ResponseEntity<>("Parameter 'id' is not correct", HttpStatus.BAD_REQUEST);
            }
        } else {
            responseEntity = new ResponseEntity<>("Parameter 'id' is empty", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @ApiOperation("Получить товар по id")
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        Product result = null;
        if (id != null) {
            result = productService.getById(id);
        }
        return result;
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
