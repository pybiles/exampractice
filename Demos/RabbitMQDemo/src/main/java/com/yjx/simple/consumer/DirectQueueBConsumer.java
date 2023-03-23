package com.yjx.simple.consumer;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectQueueBConsumer {


    @RabbitListener(queues = "directQueueB")
    public void onMsg(Message message, String msg){
        System.out.println(message);
        System.out.println("DirectQueueBConsumer "+msg);
    }



}

