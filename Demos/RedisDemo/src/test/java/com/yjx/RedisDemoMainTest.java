package com.yjx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisDemoMainTest {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedisTemplate() {

        //1.先获取到ops操作对象
        ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();//操作value是字符串的

        //2.使用ops操作数据
        opsForValue.set("k1", "v1");
        opsForValue.set("k11", "v11", 5, TimeUnit.SECONDS);
        opsForValue.setIfAbsent("k1", "v1111111111111111");
        opsForValue.setIfAbsent("k3", "v3333333333333333");

        System.out.println(opsForValue.get("k1"));
        System.out.println(opsForValue.get("k11"));

    }


    @Test
    void testStringRedisTemplate() {

        //1.先获取到ops操作对象
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();//操作value是字符串的

        //2.使用ops操作数据
        opsForValue.set("k1", "v1");
        opsForValue.set("k11", "v11", 5, TimeUnit.SECONDS);
        opsForValue.setIfAbsent("k1", "v1111111111111111");
        opsForValue.setIfAbsent("k3", "v3333333333333333");

        System.out.println(opsForValue.get("k1"));
        System.out.println(opsForValue.get("k11"));

    }

    @Test
    void testDelete() {
        Boolean deleteResult = stringRedisTemplate.delete("k1");
        System.out.println(deleteResult);
    }


}
