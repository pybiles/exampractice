package com.yjx.simple.consumer;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectQueueAConsumer {


    @RabbitListener(queues = "directQueueA")
    public void onMsg(Message message, String msg){
        System.out.println(message);
        System.out.println("DirectQueueAConsumer "+msg);
    }



}
