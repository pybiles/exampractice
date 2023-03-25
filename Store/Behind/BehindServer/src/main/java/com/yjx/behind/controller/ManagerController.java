package com.yjx.behind.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjx.behind.model.Result;
import com.yjx.dal.entity.Manager;
import com.yjx.service.ManagerService;
import com.yjx.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 * 内部员工信息 前端控制器
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@RestController
//@CrossOrigin //指定当前类下的所有接口支持跨域访问
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @PostMapping("login")
    public Result login(String username, String password){

        //结果放在一个Result对象中
        Result result = new Result<>();

        Manager manager = managerService.checkLogin(username, password);
        if (manager==null){
            result = Result.failResult("用户名或密码错误");
        }else {
            String token = JwtUtil.createTokenSingleInfo(24 * 60, "username", manager.getUserName());
            result = Result.successResult(token);
        }

        return result;
    }

    @GetMapping("getCurrentUserName")
    public Result<String> getCurrentUserName(String token){

        String username = JwtUtil.parseValueWithoutException(token, "username");

        return Result.successResult(username);
    }
    @RequestMapping("pageSearch")
    public Result<Page<Manager>> pageSearch(Integer currentPage, Integer pageSize, String keyword){

        Page<Manager> pageData = managerService.pageSearch(currentPage, pageSize, keyword);

        return Result.successResult(pageData);
    }

    @PostMapping("add")
    public Result<String> add(@RequestBody Manager manager){

        manager.setLocked("0");
        manager.setInsertTime(new Date());
        manager.setUpdateTime(new Date());
        managerService.save(manager);

        return Result.successResult("ok");

    }


    @PostMapping("update")
    public Result<String> update(@RequestBody Manager manager){

        Manager oldManager = managerService.getById(manager.getId());
        oldManager.setUserName(manager.getUserName());
        oldManager.setUserPassword(manager.getUserPassword());
        oldManager.setUpdateTime(new Date());

        managerService.updateById(oldManager);

        return Result.successResult("ok");
    }



    @DeleteMapping("delete/{id}")
    public Result<String> delete(@PathVariable Long id){

        managerService.deleteManager(id);

        return Result.successResult("ok");
    }
    @PostMapping("getRelationRoleIds")
    public Result<Set<Long>> getRelationRoleIds(Integer managerId){
        Set<Long> relationRoleIds = managerService.getRelationRoleIds(managerId);
        return Result.successResult(relationRoleIds);
    }

    @PostMapping("refreshRelationRoleIds")
    public Result<String> refreshRelationRoleIds(Long managerId, @RequestParam(name = "roleIds[]",required = false) Long[] roleIds){
        managerService.refreshRelationRoleIds(managerId,roleIds);

        return Result.successResult("ok");
    }
}

