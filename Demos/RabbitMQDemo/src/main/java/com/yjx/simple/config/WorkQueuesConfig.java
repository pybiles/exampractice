package com.yjx.simple.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkQueuesConfig {

    @Bean
    public Queue workQueues(){
        return new Queue("work_queues");
    }


}