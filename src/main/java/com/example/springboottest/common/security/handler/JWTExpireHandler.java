package com.example.springboottest.common.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：JWT过期处理
 */
public class JWTExpireHandler{

    public static void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception e) throws IOException {
        //返回json形式的错误信息
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println("{\"code\":111,\"message\":\"jwt过期!!\"}");
        httpServletResponse.getWriter().flush();
    }
}
