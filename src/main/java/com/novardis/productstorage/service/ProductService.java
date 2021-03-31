package com.novardis.productstorage.service;

import com.novardis.productstorage.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product createProduct(Product product);

    Product updateProduct(Product product);

    boolean deleteProductById(Long id);

    Product getById(Long id);


}
