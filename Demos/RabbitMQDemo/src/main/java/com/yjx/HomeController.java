package com.yjx;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/")
    public String root(){
        return "index";
    }


    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(Integer mqType){

        System.out.println(mqType);
        Date date = new Date();
        if (mqType==1){

            //生产者发送消息
            rabbitTemplate.convertAndSend("","simple_queue","helloRabbitMQ");

            return "单消费者ok "+date;
        } else if (mqType==2) {
            String msg = "一条来自生产者的消息 "+new Date();

            //生产发送消息
            rabbitTemplate.convertAndSend("","work_queues",msg);

            return "多消费者ok "+date;
        } else if (mqType==3) {

            String msg = "一条来自生产者的消息 "+new Date();
            rabbitTemplate.convertAndSend("fanoutExchange","xxx",msg);

            return "Fanout 交换机-广播模式 ok "+date;
        } else if (mqType==4) {

            String errorMsg = "一条来自生产者的 error 消息 "+new Date();
            String infoMsg = "一条来自生产者的 info 消息 "+new Date();
            String warningMsg = "一条来自生产者的 warning 消息 "+new Date();

            rabbitTemplate.convertAndSend("directExchange","error",errorMsg);
            rabbitTemplate.convertAndSend("directExchange","info",infoMsg);
            rabbitTemplate.convertAndSend("directExchange","warning",warningMsg);


            return "Direct 交换机-定向模式 ok "+date;
        } else if (mqType==5) {
            String[] routingKeys = new String[]{"fast.orange.dog","lazy.blue.chicken",
                    "lazy.orange.cat","fast.blue.rabbit","speed.green.rabbit","spedd.red.monkey"};

            for(String routingKey : routingKeys){
                String msg = "一条来自生产者的 "+routingKey+" 消息 "+new Date();
                rabbitTemplate.convertAndSend("topicExchange",routingKey,msg);
            }


            return "Topic 交换机-模糊匹配模式 "+date;
        }

        return "ok";
    }


}
