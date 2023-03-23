package com.yjx;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeDirectConfig {


    @Bean
    public Queue noDurableQueue(){
        return new Queue("noDurableQueue",false);
    }

    @Bean
    public DirectExchange directExchangeDemo2(){
        return new DirectExchange("directExchangeDemo2");
        //调用了 this(name, true, false);   第二参数 durable 指定为了true,代表交换机是需要持久化的
    }

    @Bean
    public Queue directQueueDemo2(){
        return new Queue("directQueueDemo2");
        //调用了重载构造方法 this(name, true, false, false);
        //第二参数 durable 指定为了true,代表队列是需要持久化的
    }

    @Bean
    public Binding directBindingDemo2(){
        return BindingBuilder
                .bind(directQueueDemo2())
                .to(directExchangeDemo2())
                .with("demo2");
    }



}