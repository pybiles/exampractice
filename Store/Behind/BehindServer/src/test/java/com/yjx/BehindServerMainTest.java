package com.yjx;

import com.yjx.behind.model.Result;
import com.yjx.dal.model.PermissionMenu;
import com.yjx.service.ManagerService;
import com.yjx.service.UrlPermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

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
    void sqlAndMap(){
        long a = System.currentTimeMillis();
        List<PermissionMenu> allRootMenuBySql = urlPermissionService.getAllRootMenuBySql();
        long b = System.currentTimeMillis();

        List<PermissionMenu> allRootMenuByMap = urlPermissionService.getAllRootMenuByMap();
        long c = System.currentTimeMillis();

        System.out.println("allRootMenuBySql耗时 "+(b-a)+"ms  allRootMenuByMap耗时 "+(c-b)+"ms");

    }
}
