package com.example.springboottest.common.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



public class SecurityUtils {

    /***
     * 获取当前登入的用户ID
     */
    public static Integer getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null == auth) return null;
        Integer userId = (Integer) auth.getPrincipal();
        return userId;
    }

}
