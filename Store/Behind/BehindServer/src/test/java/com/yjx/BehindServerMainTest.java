package com.yjx;

import com.yjx.service.ManagerService;
import com.yjx.service.UrlPermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class BehindServerMainTest {
    @Autowired
    ManagerService managerService;
    @Resource
    UrlPermissionService urlPermissionService;
    @Test
    void test(){
        System.out.println(managerService.list());
    }
    @Test
    void ulr(){
        System.out.println(urlPermissionService.getAllRootMenuBySql());
    }
}
