package com.example.springboottest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //不进行拦截的路径
        http
                .authorizeRequests()
                .antMatchers("/users/**").permitAll().anyRequest().authenticated()
        ;
        //这里关闭security对csrf的保护，不然post请求不了，会出现403
        http.csrf().disable();

    }

    //对swagger放行
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("swagger-ui.html")
                .antMatchers("/swagger-ui.html/**")
                .antMatchers("/v2/api-docs/**")
                .antMatchers("/webjars/springfox-swagger-ui/**")
                .antMatchers("/swagger-resources/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }


}
