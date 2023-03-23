package com.yjx.simple.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    public Queue topicQueue1(){
        return new Queue("topicQueue1");
    }


    @Bean
    public Queue topicQueue2(){
        return new Queue("topicQueue2");
    }


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }


    @Bean
    public Binding topicBind1Orange(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("*.orange.*");
    }

    @Bean
    public Binding topicBind2Rabbit(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("*.*.rabbit");
    }

    @Bean
    public Binding topicBind2lazy(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("lazy.#");
    }



}

