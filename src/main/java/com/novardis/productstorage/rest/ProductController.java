package com.novardis.productstorage.rest;

import com.novardis.productstorage.domain.Product;
import com.novardis.productstorage.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Product createProduct(@RequestBody Product product){
        Product res = productService.createProduct(product);
        return res;
    }

    @ApiOperation("Обновить товар")
    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
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

}
