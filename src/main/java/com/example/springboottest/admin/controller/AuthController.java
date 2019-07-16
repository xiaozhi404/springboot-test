package com.example.springboottest.admin.controller;

import com.example.springboottest.admin.domain.inbound.LoginInbound;
import com.example.springboottest.admin.domain.outbound.LoginOutbound;
import com.example.springboottest.admin.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("登录相关接口")
@RestController
@RequestMapping(value="/admin/auth")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("账号密码登录-jwt实现")
    @PostMapping("/login")
    public LoginOutbound loginByAccount(@RequestBody LoginInbound loginInbound) {
        return sysUserService.loginByAccount(loginInbound);
    }

}
