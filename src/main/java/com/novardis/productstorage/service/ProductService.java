package com.novardis.productstorage.service;

import com.novardis.productstorage.criteria.ProductCreatePK;
import com.novardis.productstorage.criteria.ProductUpdatePK;
import com.novardis.productstorage.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product createProduct(ProductCreatePK productCreatePK);

    Product updateProduct(ProductUpdatePK productUpdatePK);

    boolean deleteProductById(Long id);

    Product getById(Long id);


}
