package com.yjx.behind.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 结果码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;


    //便捷静态方法
    public static <R> Result<R> successResult(R data){

        Result<R> result = new Result<>();
        result.setCode(200);
        result.setMsg("ok");
        result.setData(data);

        return result;
    }

    //便捷静态方法
    public static <R> Result<R> failResult(String msg,R data){

        Result<R> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(data);

        return result;
    }
    public static Result<String> failResult(String msg){

        Result<String> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setData("");

        return result;
    }

}
