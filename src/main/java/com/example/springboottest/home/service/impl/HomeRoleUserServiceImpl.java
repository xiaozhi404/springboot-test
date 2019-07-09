package com.example.springboottest.home.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.common.mapper.HomeRoleUserMapper;
import com.example.springboottest.common.pojo.HomeRoleUser;
import com.example.springboottest.home.service.HomeRoleUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 前台角色-用户关联表 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-09
 */
@Service
public class HomeRoleUserServiceImpl extends ServiceImpl<HomeRoleUserMapper, HomeRoleUser> implements HomeRoleUserService {

}
