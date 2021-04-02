package com.novardis.productstorage.service;

import com.novardis.productstorage.criteria.ProductAttributePK;
import com.novardis.productstorage.domain.Product;

public interface ProductAttributeService {

    Product addAttributeToProduct(ProductAttributePK param);

}
