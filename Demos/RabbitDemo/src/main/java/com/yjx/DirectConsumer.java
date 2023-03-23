package com.yjx;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DirectConsumer {

    @RabbitListener(queues = "directQueueDemo2")
    public void onMsg(String msg, Message message, Channel channel) throws IOException {

        //默认是自动ack, 方法执行完成之后,自动向MQ队列发送一个ack确认,然后队列就认为消息被正常消费了,然后将消息从队列中删除.

        try {
            System.out.println("DirectConsumer 接收到消息 " + msg);

            int a = 3 / 0;
        }catch (Exception e){
            //log ...
        }

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }




}
