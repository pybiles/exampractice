package com.yjx.shopping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjx.entity.User;
import com.yjx.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusDemoMainTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void testMapper() {

        User tom = userMapper.getUserByName("Tom");
        System.out.println(tom);
    }

    @Test
    void testAnnotation() {
        List<User> userList = userMapper.likeEmail("bao");
        System.out.println(userList);
    }
    @Test
    void testPlusMapper(){
        User user = userMapper.selectById(3);
        System.out.println(user);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("email","bao");

        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);

    }
}