package com.example.springboottest.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.admin.service.SysRolePermisionService;
import com.example.springboottest.common.mapper.SysRolePermisionMapper;
import com.example.springboottest.common.pojo.SysRolePermision;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色-权限关联表 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Service
public class SysRolePermisionServiceImpl extends ServiceImpl<SysRolePermisionMapper, SysRolePermision> implements SysRolePermisionService {

}
