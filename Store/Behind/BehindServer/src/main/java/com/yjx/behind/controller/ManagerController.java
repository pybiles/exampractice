package com.yjx.behind.controller;


import com.yjx.behind.model.Result;
import com.yjx.dal.entity.Manager;
import com.yjx.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内部员工信息 前端控制器
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@RestController
@CrossOrigin //指定当前类下的所有接口支持跨域访问
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
            result = Result.successResult("登录成功");
        }

        return result;
    }


}

