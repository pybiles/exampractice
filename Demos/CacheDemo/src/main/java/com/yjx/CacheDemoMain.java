package com.yjx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching   //开启注解缓存
public class CacheDemoMain {
    public static void main(String[] args) {
        SpringApplication.run(CacheDemoMain.class);
    }
}