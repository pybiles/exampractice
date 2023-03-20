package com.yjx.front.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestSendEmailConfig {

    @Bean
    public Queue testSendEmail(){
        return new Queue("test_send_email");
    }

}
