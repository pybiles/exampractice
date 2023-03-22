package com.yjx.behind.config;


import com.yjx.behind.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理机制
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {

        //log.......
        //其它处理

        e.printStackTrace();

        return Result.failResult("网络服务异常,请稍后再试");
    }


}
