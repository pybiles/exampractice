package com.yjx.behind.controller;


import com.yjx.behind.model.Result;
import com.yjx.dal.model.PermissionMenu;
import com.yjx.service.UrlPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    public Result<List<PermissionMenu>> all(){
        List<PermissionMenu> allRootMenuBySql = urlPermissionService.getAllRootMenuBySql();

        return Result.successResult(allRootMenuBySql);
    }

}

