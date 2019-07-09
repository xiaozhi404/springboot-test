package com.example.springboottest.common.security.filter;

import com.example.springboottest.common.security.dto.AdminTokenDTO;
import com.example.springboottest.common.utils.JWTUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 作用：解析token,并将权限放入上下文中
 * 注意：没有token也应该继续执行，权限的认证由框架执行
 */
public class AdminAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String jwtToken = request.getHeader( HttpHeaders.AUTHORIZATION);
        //判断是否有token
        if (StringUtils.isEmpty(jwtToken)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            AdminTokenDTO adminTokenDTO = JWTUtils.extractAdminToken(jwtToken);
            List<SimpleGrantedAuthority> authoritys = adminTokenDTO.getAuthorities().stream()
                    .map(
                        authority -> new SimpleGrantedAuthority(authority)
                    ).collect(Collectors.toList());
            //权限和角色都可放进上下文，由框架进行权限判断
            //当设置进去context之后，意味着该请求已经登录过了
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            adminTokenDTO.getSysUserId(),   //表单认证方式：填充的一般是用户名
                            null,   //表单认证方式：填充的一般是密码
                            authoritys
                    )
            );
        } catch (Exception e) {
            throw new BadCredentialsException(e.getLocalizedMessage());
        }
        chain.doFilter(request, response);
    }
}
