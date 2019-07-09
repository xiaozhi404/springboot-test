package com.example.springboottest.home.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.common.mapper.HomeRoleMapper;
import com.example.springboottest.common.pojo.HomeRole;
import com.example.springboottest.home.service.HomeRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 前台角色表 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-09
 */
@Service
public class HomeRoleServiceImpl extends ServiceImpl<HomeRoleMapper, HomeRole> implements HomeRoleService {

}
