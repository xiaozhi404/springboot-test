package com.example.springboottest.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.admin.service.SysRoleUserService;
import com.example.springboottest.common.mapper.SysRoleUserMapper;
import com.example.springboottest.common.pojo.SysRoleUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色-用户关联表 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-08
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

}
