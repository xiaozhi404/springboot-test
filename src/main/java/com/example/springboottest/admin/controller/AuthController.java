package com.example.springboottest.admin.controller;

import com.example.springboottest.admin.domain.inbound.LoginInbound;
import com.example.springboottest.admin.domain.outbound.LoginOutbound;
import com.example.springboottest.admin.service.SysUserService;
import com.example.springboottest.common.security.dto.AccessToken;
import com.example.springboottest.common.security.dto.TokenOutbound;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@Api("登录相关接口")
@RestController
@RequestMapping(value="/admin/auth")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("账号密码登录-获取token")
    @PostMapping("/login")
    public LoginOutbound loginByAccount(@RequestBody LoginInbound loginInbound) {
        return sysUserService.loginByAccount(loginInbound);
    }

    /**
     * 刷新token
     */
    @GetMapping(value = "/refreshToken")
    public AccessToken refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return sysUserService.refreshToken(authorization);
    }

}
