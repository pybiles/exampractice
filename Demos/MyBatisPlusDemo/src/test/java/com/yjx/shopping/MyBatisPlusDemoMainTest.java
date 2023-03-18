package com.yjx.shopping;

import com.yjx.entity.User;
import com.yjx.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusDemoMainTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void testMapper(){

        User tom = userMapper.getUserByName("Tom");
        System.out.println(tom);

    }


}