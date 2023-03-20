package com.yjx.front.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TestSendEmailConsumer {

    @Autowired
    JavaMailSender javaMailSender;

    @RabbitListener(queues = "test_send_email")
    public void onMsg(Message message, Map<String,String> infoMap){

        System.out.println(infoMap);
        System.out.println(message);

        System.out.println("接收发送邮件的需求 "+new Date());
        sendEmail(infoMap.get("email"),infoMap.get("subject"),infoMap.get("text"));
        System.out.println("邮件发送成功 "+new Date());
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
