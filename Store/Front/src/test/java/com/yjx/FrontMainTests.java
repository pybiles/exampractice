package com.yjx;

import com.yjx.dal.entity.ProductType;
import com.yjx.service.ProductService;
import com.yjx.service.ProductTypeService;
import com.yjx.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

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
