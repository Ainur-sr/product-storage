package com.novardis.productstorage.service;

import com.novardis.productstorage.criteria.ProductAttributeCreatePK;
import com.novardis.productstorage.criteria.ProductAttributeDeletePK;
import com.novardis.productstorage.domain.Product;

public interface ProductAttributeService {

    Product addAttributeToProduct(ProductAttributeCreatePK param);

    Product deleteAttributeProduct(ProductAttributeDeletePK param);

}
