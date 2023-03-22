package com.yjx;

import com.yjx.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BehindServerMainTest {
    @Autowired
    ManagerService managerService;
    @Test
    void test(){
        System.out.println(managerService.list());
    }
}
