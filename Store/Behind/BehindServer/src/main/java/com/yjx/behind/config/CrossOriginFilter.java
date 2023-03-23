package com.yjx.behind.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CrossOriginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //在响应头里加上允许跨域访问
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");

        //允许自定义请求头
        httpServletResponse.addHeader("Access-Control-Allow-Headers","*");

        //放行
        chain.doFilter(request,response);
    }
}
