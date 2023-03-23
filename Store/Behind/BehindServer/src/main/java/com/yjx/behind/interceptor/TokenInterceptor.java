package com.yjx.behind.interceptor;

import com.yjx.service.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从请求头里拿到token进行解析,如果解析不成功,那就直接返回"toLogin"
        String token = request.getHeader("token");
        String username = JwtUtil.parseValueWithoutException(token, "username");
        if (StringUtils.isEmpty(username)){
            response.getWriter().write("toLogin");
            return false;
        }


        return true;
    }
}