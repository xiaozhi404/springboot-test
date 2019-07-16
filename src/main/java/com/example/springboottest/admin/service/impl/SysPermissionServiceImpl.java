package com.example.springboottest.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.admin.service.SysPermissionService;
import com.example.springboottest.common.mapper.SysPermissionMapper;
import com.example.springboottest.common.pojo.SysPermission;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

}
