package com.novardis.productstorage.repository;

public interface AttributeLinkRepository {

    Long createIndex();
    boolean deleteByAttributeId(Long attributeId);
    boolean deleteByProductId(Long productId);

}
