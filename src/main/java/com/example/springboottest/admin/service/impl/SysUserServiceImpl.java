package com.example.springboottest.admin.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.admin.domain.inbound.LoginInbound;
import com.example.springboottest.admin.domain.outbound.LoginOutbound;
import com.example.springboottest.admin.domain.outbound.UserOutbound;
import com.example.springboottest.admin.service.SysRoleService;
import com.example.springboottest.admin.service.SysRoleUserService;
import com.example.springboottest.common.exception.ExceptionMsgEnum;
import com.example.springboottest.common.exception.dto.BusinessException;
import com.example.springboottest.common.mapper.SysUserMapper;
import com.example.springboottest.common.pojo.SysRole;
import com.example.springboottest.common.pojo.SysRoleUser;
import com.example.springboottest.common.pojo.SysUser;
import com.example.springboottest.admin.service.SysUserService;
import com.example.springboottest.common.security.dto.AccessToken;
import com.example.springboottest.common.security.dto.AdminTokenDTO;
import com.example.springboottest.common.security.dto.TokenOutbound;
import com.example.springboottest.common.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-06-28
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public LoginOutbound loginByAccount(LoginInbound loginInbound) {
        SysUser sysUser = baseMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name", loginInbound.getUserName()));

        if (null == sysUser) {
            throw new BusinessException(ExceptionMsgEnum.ACCOUNT_NOT_FOUND);
        } else if (!loginInbound.getPassword().equals(sysUser.getPassword())) {
            throw new BusinessException(ExceptionMsgEnum.PASSWORD_WRONG);
        } else {
            //获取用户拥有的所有的角色标示，构建token
            List<SysRoleUser> roleUserList = sysRoleUserService.list(new QueryWrapper<SysRoleUser>().eq("sys_user_id", sysUser.getId()));
            List<Integer> roleIds = roleUserList.stream().map(SysRoleUser::getSysRoleId).collect(Collectors.toList());
            List<String> roleAuths = sysRoleService.listByIds(roleIds).stream().map(SysRole::getAuth).collect(Collectors.toList());

            return new LoginOutbound(
                  new UserOutbound(
                          sysUser.getId(),
                          sysUser.getUserName()
                  ),
                    JWTUtils.buildAdminToken(
                            new AdminTokenDTO(
                                    sysUser.getId(),
                                    roleAuths
                            )
                    )
            );
        }
    }

    @Override
    public AccessToken refreshToken(String refreshToken) {
        return null;
    }

}
