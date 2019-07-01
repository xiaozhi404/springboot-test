package com.example.springboottest.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api("hello接口管理")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation("获取hello world字符串")
    @RequestMapping("/world")
    public String hello() {
        return "hello world";
    }

}
