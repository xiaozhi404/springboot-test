package com.example.springboottest.home.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboottest.admin.domain.inbound.LoginInbound;
import com.example.springboottest.admin.domain.outbound.LoginOutbound;
import com.example.springboottest.common.pojo.HomeUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前台用户 服务类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-09
 */
public interface HomeUserService extends IService<HomeUser> {

    /**
     * 通过用户名登录
     * @param loginInbound
     * @return
     */
    LoginOutbound loginByAccount(LoginInbound loginInbound);

    void loginByAccountWithSession(LoginInbound loginInbound, HttpSession session);
}
