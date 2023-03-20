package com.yjx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheDemoMainTest {
    @Autowired
    ProductTypeService productTypeService;
    @Test
    void test(){
        System.out.println(productTypeService.getAll());
    }
}
