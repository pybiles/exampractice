package com.yjx.mapper;

import com.yjx.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper   //指定当前类是需要mybatis处理的mapper类
public interface UserMapper {

    public User getUserByName(String name);


}