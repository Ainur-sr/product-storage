package com.novardis.productstorage.service;

import com.novardis.productstorage.criteria.ProductAttributePK;
import com.novardis.productstorage.criteria.ProductAttributeDeletePK;
import com.novardis.productstorage.domain.Product;

public interface ProductAttributeService {

    Product addProductAttribute(ProductAttributePK param);

    Product updateProductAttribute(ProductAttributePK param);

    Product deleteProductAttribute(ProductAttributeDeletePK param);

}
