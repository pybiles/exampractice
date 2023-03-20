package com.yjx.simple.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleConsumer {

    //消费者,监听 simple_queue 队列
    @RabbitListener(queues = "simple_queue")
    public void onMsg(Message message){
        System.out.println(message);
    }


}

