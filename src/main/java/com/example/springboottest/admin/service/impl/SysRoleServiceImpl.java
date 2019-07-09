package com.example.springboottest.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.admin.service.SysRoleService;
import com.example.springboottest.common.mapper.SysRoleMapper;
import com.example.springboottest.common.pojo.SysRole;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-08
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
