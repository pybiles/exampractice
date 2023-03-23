package com.yjx.simple.config;



import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    @Bean
    public Queue directQueueA(){
        return new Queue("directQueueA");
    }


    @Bean
    public Queue directQueueB(){
        return new Queue("directQueueB");
    }


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }


    @Bean
    public Binding directBindAError(){
        return BindingBuilder.bind(directQueueA()).to(directExchange()).with("error");
    }


    @Bean
    public Binding directBindBInfo(){
        return BindingBuilder.bind(directQueueB()).to(directExchange()).with("info");
    }


    @Bean
    public Binding directBindBError(){
        return BindingBuilder.bind(directQueueB()).to(directExchange()).with("error");
    }

    @Bean
    public Binding directBindBWarning(){
        return BindingBuilder.bind(directQueueB()).to(directExchange()).with("warning");
    }


}
