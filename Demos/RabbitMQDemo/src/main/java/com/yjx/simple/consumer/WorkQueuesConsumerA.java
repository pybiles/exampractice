package com.yjx.simple.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkQueuesConsumerA {

    @RabbitListener(queues = "work_queues")
    //这里这个  Message , msg   可以对标   Request ,具体请求参数
    public void onMsg(Message message,String msg){
        System.out.println(message);
        System.out.println("WorkQueuesConsumerA   "+msg);
    }
}