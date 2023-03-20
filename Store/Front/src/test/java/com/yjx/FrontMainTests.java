package com.yjx;

import com.yjx.service.ProductService;
import com.yjx.service.ProductTypeService;
import com.yjx.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class FrontMainTests {
    @Autowired
    UserService userService;
    @Resource
    ProductTypeService productTypeService;
    @Resource
    ProductService productService;

    @Test
    void test() {
        System.out.println(userService.list());

    }
    @Test
    void test1(){
        System.out.println(productTypeService.list());
    }
    @Test
    void test2(){
        System.out.println(productService.topN(7));
    }
}
