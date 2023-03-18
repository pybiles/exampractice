package com.yjx;

import com.yjx.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FrontMainTests {
@Autowired
    UserService  userService;
    @Test
    void test() {
        System.out.println(userService.list());
    }
}
