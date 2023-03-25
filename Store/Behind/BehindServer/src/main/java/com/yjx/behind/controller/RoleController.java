package com.yjx.behind.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjx.behind.model.Result;
import com.yjx.dal.entity.Role;
import com.yjx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("pageSearch")
    public Result<Page<Role>> pageSearch(Integer currentPage, Integer pageSize, String keyword){

        Page<Role> pageData = roleService.pageSearch(currentPage, pageSize, keyword);

        return Result.successResult(pageData);
    }

    @PostMapping("add")
    public Result<String> add(@RequestBody Role role){

        role.setInsertTime(new Date());
        role.setUpdateTime(new Date());
        roleService.save(role);

        return Result.successResult("ok");

    }


    @PostMapping("update")
    public Result<String> update(@RequestBody Role role){

        Role oldRole = roleService.getById(role.getId());
        oldRole.setName(role.getName());
        oldRole.setCode(role.getCode());
        oldRole.setUpdateTime(new Date());

        roleService.updateById(oldRole);

        return Result.successResult("ok");
    }



    @DeleteMapping("delete/{id}")
    public Result<String> delete(@PathVariable Long id){

        roleService.deleteRole(id);

        return Result.successResult("ok");
    }
    @PostMapping("getRelationPermissionIds")
    public Result<Set<Long>> getRelationPermissionIds(Integer roleId){

        Set<Long> relationPermissionIds = roleService.getRelationPermissionIds(roleId);

        return Result.successResult(relationPermissionIds);
    }

    @PostMapping("refreshRelationPermissionIds")
    // required = false  用来兼容所有权限都被移除掉的情况
    public Result<String> refreshRelationPermissionIds(Long roleId, @RequestParam(name = "permissionIds[]",required = false) Long[] permissionIds){

        roleService.refreshRelationPermissionIds(roleId,permissionIds);

        return Result.successResult("ok");
    }
}

