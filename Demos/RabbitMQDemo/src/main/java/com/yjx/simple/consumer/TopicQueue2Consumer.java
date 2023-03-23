package com.yjx.simple.consumer;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicQueue2Consumer {


    @RabbitListener(queues = "topicQueue2")
    public void onMsg(Message message, String msg){
//        System.out.println(message);
        System.out.println("TopicQueue2Consumer "+msg);
    }



}
