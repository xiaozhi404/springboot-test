package com.example.springboottest.controller;

import com.example.springboottest.pojo.User;
import com.example.springboottest.tkMapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "用户模块", description = "用户相关接口")
@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value="获取用户列表", notes="")
    @GetMapping(value="/")
    public List<User> getUserList() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/")
    public String postUser(@RequestBody User user) {
        userMapper.insertSelective(user);
        return "insert success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return "update success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userMapper.deleteByPrimaryKey(id);
        return "delete success";
    }

}
