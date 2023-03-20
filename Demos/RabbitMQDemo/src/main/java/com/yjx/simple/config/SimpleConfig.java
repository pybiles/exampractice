package com.yjx.simple.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {


    @Bean
    //创建一个简单的消息队列simple_queue
    public Queue simpleQueue(){
        return new Queue("simple_queue");
    }

}