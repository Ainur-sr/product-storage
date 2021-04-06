package com.novardis.productstorage;

import com.novardis.productstorage.rest.ProductController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductDtoStorageApplicationTests {

    @Autowired
    private ProductController productController;

    @Test
    void contextLoads() {
        Assertions.assertThat(productController).isNotNull();
    }

}
