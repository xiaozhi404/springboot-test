package com.example.springboottest.home.controller;


import com.example.springboottest.common.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前台用户 前端控制器
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-09
 */
@RestController
@RequestMapping("/home/home-user")
public class HomeUserController {

    @ApiOperation("获取用户Id")
    @GetMapping("/userId")
    public Integer getId() {

        Integer userId = SecurityUtils.getUserId();
        return userId;
    }
}

