package com.example.springboottest.common.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：处理未登录或无权限时触发的操作
 * 作用：没有携带token或者token无效异常处理
 */
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
      //返回json形式的错误信息
      httpServletResponse.setCharacterEncoding("UTF-8");
      httpServletResponse.setContentType("application/json");

      httpServletResponse.getWriter().println("{\"code\":401,\"message\":\"没有携带token或者token无效！\"}");
      httpServletResponse.getWriter().flush();
  }

}