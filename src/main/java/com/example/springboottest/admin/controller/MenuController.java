package com.example.springboottest.admin.controller;


import com.example.springboottest.admin.domain.outbound.MenuOutbound;
import com.example.springboottest.admin.service.MenuService;
import com.example.springboottest.common.pojo.SysUser;
import com.example.springboottest.common.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 按钮表，控制到每个菜单的每个按钮 前端控制器
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("获取用户菜单列表")
    @GetMapping("/")
    public List<MenuOutbound> getMenuList() throws Exception {

       return menuService.getMenuList(SecurityUtils.getUserId());
    }
}

