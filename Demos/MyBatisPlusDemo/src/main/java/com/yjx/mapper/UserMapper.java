package com.yjx.mapper;

import com.yjx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper   //指定当前类是需要mybatis处理的mapper类
public interface UserMapper {

    User getUserByName(String name);

    //    @Select()
    //    @Delete()
    //    @Update()
    //    @Insert()
    @Select("select * from user where email like '%${keyword}%'")
    //根据关键字查询user信息
    List<User> likeEmail(String keyword);


}
