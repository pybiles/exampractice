package com.yjx.front.controller;

import com.google.code.kaptcha.Producer;
import com.yjx.dal.entity.User;
import com.yjx.service.UserService;
import com.yjx.service.util.JwtUtil;
import com.yjx.service.util.Md5Util;
import com.yjx.service.util.UserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserService userService;
    @Autowired
    Producer producer;

    @RequestMapping("getEmailCode")
    public String getEmailCode(String email) {

        //使用正则表达式判断邮箱地址是否正确
        String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";

        //p{Alpha}:内容是必选的，和字母字符[\p{Lower}\p{Upper}]等价。如：200896@163.com不是合法的。
        //w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
        //[a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
        //[.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
        //p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
        if (email == null || !email.matches(format)) {
            return "邮箱地址非法";
        }

        //发送邮箱验证码
        String code = "0000";

        //发送邮件
        sendEmail(email, "您的蜗牛书店注册验证码", "您的验证码为 " + code + " ,有效期为5分钟,请尽快使用");

        //保存到redis中,有效期5分钟
        stringRedisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);

        return "ok";
    }

    private void sendEmail(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xuanyuanmoli@qq.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    @RequestMapping("register")
    public String register(String username, String password, String repass, String email, String emailCode) {

        //校验邮箱验证码
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(emailCode) || !emailCode.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(email))) {
            return "邮箱或邮箱验证码错误";
        }

        //校验密码
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(repass) || !password.equals(repass)) {
            return "两次输入密码有误";
        }
        synchronized (username.intern()) { //用户名需要保证唯一性
            //方案1: 数据库里设置username列唯一索引
            //方案2: 程序里先检查该用户是否存在,如果已经已经就拒绝注册

            User userByAccount = userService.getUserByUsername(username);
            if (StringUtils.isEmpty(username) || userByAccount != null) {
                return "用户名已被占用";
            }

            //写入用户
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(Md5Util.encode(password));
            newUser.setEmail(email);

            //        Thread.sleep(5000);
            try {
                System.out.println(Thread.currentThread().getName() + " 用户名 " + username + " 唯一校验通过,准备写入用户信息,模拟比较耗时的情况");
                TimeUnit.SECONDS.sleep(10);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            userService.save(newUser);
        }

        System.out.println(Thread.currentThread().getName() + " 用户名 " + username + " 注册成功");
        //发送欢迎邮件
        sendEmail(email, "Welcome to Our Animals", "Have a good travel!");
        return "ok";
    }

    @RequestMapping("kaptchaCode")
    public void kaptchaCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //1.生成验证码字符串
        String kaptchaCode = producer.createText();

        //保存验证码到session中
        request.getSession().setAttribute("kaptchaCode", kaptchaCode);

        //2.字符串转为图片
        BufferedImage image = producer.createImage(kaptchaCode);

        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", response.getOutputStream());

    }

    @RequestMapping("login")
    public String login(String username, String password, String code, HttpServletRequest request, HttpServletResponse response) {

        String kaptchaCode = (String) request.getSession().getAttribute("kaptchaCode");
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(kaptchaCode) || !code.equalsIgnoreCase(kaptchaCode)) {
            return "验证码错误";
        }

        User userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null) {
//            return "用户名错误";
            return "用户名或密码错误";
        }

        if (!userByUsername.getPassword().equalsIgnoreCase(Md5Util.encode(password))) {
            return "用户名或密码错误";
        }
        UserTokenUtil.createUserToken(userByUsername.getUsername(), response, stringRedisTemplate);
        return "ok";
    }

    //获取当前用户信息
    @RequestMapping("getCurrentUserUsername")
    public String getCurrentUserUsername(HttpServletRequest request) {

        String userUsername = UserTokenUtil.getUserUsername(request);

        return userUsername;
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        UserTokenUtil.deleteUserToken(response, request, stringRedisTemplate);
        return "ok";
    }

}

