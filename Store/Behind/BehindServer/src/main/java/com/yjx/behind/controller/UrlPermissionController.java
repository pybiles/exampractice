package com.yjx.behind.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjx.behind.model.Result;
import com.yjx.dal.entity.UrlPermission;
import com.yjx.dal.model.PermissionMenu;
import com.yjx.service.UrlPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单和接口权限定义 前端控制器
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@RestController
@RequestMapping("/urlPermission")
public class UrlPermissionController {
    @Autowired
    UrlPermissionService urlPermissionService;

    @RequestMapping("all")
    public Result<List<PermissionMenu>> all() {
        long a = System.currentTimeMillis();
        List<PermissionMenu> allRootMenuBySql = urlPermissionService.getAllRootMenuBySql();
        long b = System.currentTimeMillis();

        List<PermissionMenu> allRootMenuByMap = urlPermissionService.getAllRootMenuByMap();
        long c = System.currentTimeMillis();

        System.out.println("allRootMenuBySql耗时 " + (b - a) + "ms  allRootMenuByMap耗时 " + (c - b) + "ms");

        return Result.successResult(allRootMenuByMap);
    }

    @RequestMapping("pageSearch")
    public Result<Page<UrlPermission>> pageSearch(Integer currentPage, Integer pageSize, String keyword) {

        Page<UrlPermission> permissionPage = urlPermissionService.pageSearch(currentPage, pageSize, keyword);

        return Result.successResult(permissionPage);
    }
    @RequestMapping("add")
    public Result<String> add(UrlPermission urlPermission){

        //数据校验.......

        urlPermissionService.save(urlPermission);


        return Result.successResult("ok");
    }
    @PostMapping("update")
    public Result<String> update(@RequestBody UrlPermission urlPermission){

        //数据校验....
        UrlPermission oriUrlPermission = urlPermissionService.getById(urlPermission.getId());
        oriUrlPermission.setName(urlPermission.getName());
        oriUrlPermission.setUrl(urlPermission.getUrl());
        oriUrlPermission.setUrlType(urlPermission.getUrlType());
        oriUrlPermission.setParentId(urlPermission.getParentId());
        oriUrlPermission.setUpdateTime(new Date());

        urlPermissionService.updateById(oriUrlPermission);

        return Result.successResult("ok");

    }

}

