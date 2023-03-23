package com.yjx.front.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SendEmailConsumer {

    @Autowired
    JavaMailSender javaMailSender;

    @RabbitListener(queues = "sendEmailQueue")
    public void onMsg(Message message, Map<String,String> infoMap, Channel channel) throws IOException {

        try {
            System.out.println(infoMap);
            System.out.println(message);

            System.out.println("接收发送邮件的需求 " + new Date());
            sendEmail(infoMap.get("email"), infoMap.get("subject"), infoMap.get("text"));
            System.out.println("邮件发送成功 " + new Date());

        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            return;
        }

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }



    private void sendEmail(String toEmail,String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xuanyuanmoli@qq.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);

        try {
            TimeUnit.SECONDS.sleep(30);  //模拟邮箱服务器高延时
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(message);
    }




}
