package com.example.springboottest.admin.controller;

import com.example.springboottest.common.pojo.SysUser;
import com.example.springboottest.admin.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api("用户相关接口")
@RestController
@RequestMapping(value="/admin/users")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @ApiOperation("获取用户列表")
    @GetMapping(value="/")
    public List<SysUser> getUserList() {
        List<SysUser> users = userService.list();
        return users;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @PostMapping("/")
    public String postUser(@RequestBody SysUser user) {
        userService.save(user);
        return "insert success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public SysUser getUser(@PathVariable Long id) {
        SysUser user = userService.getById(id);
        return user;
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "SysUser")
    })
    @PutMapping
    public String putUser(@RequestBody SysUser user) {
        userService.updateById(user);
        return "update success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeById(id);
        return "delete success";
    }

}
