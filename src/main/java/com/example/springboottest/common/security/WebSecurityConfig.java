package com.example.springboottest.common.security;

import com.example.springboottest.common.security.filter.AdminAuthenticationFilter;
import com.example.springboottest.common.security.filter.HomeAuthenticationFilter;
import com.example.springboottest.common.security.handler.EntryPointUnauthorizedHandler;
import com.example.springboottest.common.security.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 描述：前后台鉴权
 */
@EnableWebSecurity
public class WebSecurityConfig {


    @Configuration
    @Order(1)
    public static class HomeSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/home/**")//若是有两个adapter,必须配置该http的作用域
                    .cors()
                    .and()
                    .csrf().disable()//使用了jwt,故不需要防止csrf
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //定制session策略,不创建和使用session
                    .and()
                    .authorizeRequests()
                    //permitAll 该请求不需要登录和权限认证
                    //其它没配置的路径需要登录才能访问
                    .antMatchers( HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/home/auth/login").permitAll()
                    .antMatchers("/home/hello/**").hasRole("SUPER_VIP")  //用来认证角色，security会自动帮该字符串加前戳ROLE_，故数据库的存储也要加前缀ROLE_
                    //.antMatchers("/admin/users/**").hasAuthority("super_admin") //鉴定权限
                    .anyRequest().authenticated()
                    .and()  //注意拦截器不能用@compone, 而且拦截器只是添加到chain中，不会替代默认的认证拦截器
                    .addFilterBefore(new HomeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling()    //处理异常
                    .authenticationEntryPoint(new EntryPointUnauthorizedHandler())
                    .accessDeniedHandler(new MyAccessDeniedHandler())
            ;
        }

        @Override
        public void configure(WebSecurity web) {
            setWebSecurityConfig(web);
        }
    }

    @Configuration
    @Order(2)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")//若是有两个adapter,必须配置该http的作用域
                    .cors()
                    .and()
                    .csrf().disable()//使用了jwt,故不需要防止csrf
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //定制session策略,不创建和使用session
                    .and()
                    .authorizeRequests()
                    //permitAll 该请求不需要登录和权限认证
                    //其它没配置的路径需要登录才能访问
                    .antMatchers( HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/admin/login").permitAll()
                    .antMatchers("/admin/users/**").hasRole("SUPER_ADMIN")  //用来认证角色，security会自动帮该字符串加前戳ROLE_，故数据库的存储也要加前缀ROLE_
                    //.antMatchers("/admin/users/**").hasAuthority("super_admin") //鉴定权限
                    .anyRequest().authenticated()
                    .and()  //注意拦截器不能用@compone, 而且拦截器只是添加到chain中，不会替代默认的认证拦截器
                    .addFilterBefore(new AdminAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling()    //处理异常
                    .authenticationEntryPoint(new EntryPointUnauthorizedHandler())
                    .accessDeniedHandler(new MyAccessDeniedHandler())
            ;
        }

        @Override
        public void configure(WebSecurity web) {
            setWebSecurityConfig(web);
        }
    }

    /**
     * 配置静态资源
     * @param web
     */
    public static void setWebSecurityConfig(WebSecurity web) {
        web.ignoring()
                .antMatchers("swagger-ui.html")
                .antMatchers("/v2/api-docs/**")
                .antMatchers("/webjars/springfox-swagger-ui/**")
                .antMatchers("/swagger-ui.html/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/favicon.ico")
        ;
    }


}
