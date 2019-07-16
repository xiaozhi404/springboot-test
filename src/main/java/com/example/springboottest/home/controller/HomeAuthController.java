package com.example.springboottest.home.controller;

import com.example.springboottest.admin.domain.inbound.LoginInbound;
import com.example.springboottest.admin.domain.outbound.LoginOutbound;
import com.example.springboottest.admin.service.SysUserService;
import com.example.springboottest.home.service.HomeUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("前台登录相关接口")
@RestController
@RequestMapping(value="/home/auth")
public class HomeAuthController {

    @Autowired
    private HomeUserService homeUserService;

    @ApiOperation("账号密码登录")
    @PostMapping("/login")
    public LoginOutbound loginByAccount(@RequestBody LoginInbound loginInbound) {
        return homeUserService.loginByAccount(loginInbound);
    }

}
