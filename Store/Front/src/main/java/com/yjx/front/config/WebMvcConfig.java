package com.yjx.front.config;

import com.yjx.front.interceptors.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    JwtInterceptor jwtInterceptor;



    //直接注册一些ViewController
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //WebMvcConfigurer.super.addViewControllers(registry);

        //配置  访问urlPath时对应的视图模板
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/singleProduct.html").setViewName("singleProduct");
        registry.addViewController("/productsOfType.html").setViewName("productsOfType");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/myCart.html").setViewName("myCart");

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**");

    }
}