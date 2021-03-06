package com.example.springboottest.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboottest.admin.domain.inbound.LoginInbound;
import com.example.springboottest.admin.domain.outbound.LoginOutbound;
import com.example.springboottest.common.pojo.SysUser;
import com.example.springboottest.common.security.dto.AccessToken;
import com.example.springboottest.common.security.dto.TokenOutbound;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-06-28
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过账号和密码登录
     * @param loginInbound
     * @return
     */
    LoginOutbound loginByAccount(LoginInbound loginInbound);

    /**
     * 刷新token
     * @param authorization
     * @return
     */
    AccessToken refreshToken(String authorization);
}
