package com.example.springboottest.home.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.admin.domain.inbound.LoginInbound;
import com.example.springboottest.admin.domain.outbound.LoginOutbound;
import com.example.springboottest.admin.domain.outbound.UserOutbound;
import com.example.springboottest.common.exception.ExceptionMsgEnum;
import com.example.springboottest.common.exception.dto.BusinessException;
import com.example.springboottest.common.mapper.HomeUserMapper;
import com.example.springboottest.common.pojo.*;
import com.example.springboottest.common.security.dto.HomeTokenDTO;
import com.example.springboottest.common.utils.JWTUtils;
import com.example.springboottest.home.dto.HomeUserInfo;
import com.example.springboottest.home.service.HomeRoleService;
import com.example.springboottest.home.service.HomeRoleUserService;
import com.example.springboottest.home.service.HomeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前台用户 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-09
 */
@Service
public class HomeUserServiceImpl extends ServiceImpl<HomeUserMapper, HomeUser> implements HomeUserService {

    @Autowired
    private HomeRoleUserService homeRoleUserService;

    @Autowired
    private HomeRoleService homeRoleService;

    @Override
    public LoginOutbound loginByAccount(LoginInbound loginInbound) {

        HomeUser homeUser = baseMapper.selectOne(new QueryWrapper<HomeUser>().eq("user_name", loginInbound.getUserName()));

        if (null == homeUser) {
            throw new BusinessException(ExceptionMsgEnum.ACCOUNT_NOT_FOUND);
        } else if (!loginInbound.getPassword().equals(homeUser.getPassword())) {
            throw new BusinessException(ExceptionMsgEnum.PASSWORD_WRONG);
        } else {
            //获取用户拥有的所有的角色标示，构建token
            List<HomeRoleUser> roleUserList = homeRoleUserService.list(new QueryWrapper<HomeRoleUser>().eq("home_user_id", homeUser.getId()));
            List<Long> roleIds = roleUserList.stream().map(HomeRoleUser::getHomeRoleId).collect(Collectors.toList());
            List<String> roleAuths = homeRoleService.listByIds(roleIds).stream().map(HomeRole::getAuth).collect(Collectors.toList());
            return new LoginOutbound(
                    new UserOutbound(
                            homeUser.getId(),
                            homeUser.getUserName()
                    ),
                    JWTUtils.buildHomeToken(
                            new HomeTokenDTO(
                                    homeUser.getId(),
                                    roleAuths
                            )
                    )
            );
        }

    }


    @Override
    public void loginByAccountWithSession(LoginInbound loginInbound, HttpSession session) {
        HomeUser homeUser = baseMapper.selectOne(new QueryWrapper<HomeUser>().eq("user_name", loginInbound.getUserName()));

        if (null == homeUser) {
            throw new BusinessException(ExceptionMsgEnum.ACCOUNT_NOT_FOUND);
        } else if (!loginInbound.getPassword().equals(homeUser.getPassword())) {
            throw new BusinessException(ExceptionMsgEnum.PASSWORD_WRONG);
        } else {
            //保存到session中，用redis实现，redis中到数据结构为hash
            // 默认前缀：[spring:session:+"key",value]
            List<HomeRoleUser> roleUserList = homeRoleUserService.list(new QueryWrapper<HomeRoleUser>().eq("home_user_id", homeUser.getId()));
            List<Long> roleIds = roleUserList.stream().map(HomeRoleUser::getHomeRoleId).collect(Collectors.toList());
            List<String> roleAuths = homeRoleService.listByIds(roleIds).stream().map(HomeRole::getAuth).collect(Collectors.toList());
            session.setAttribute("home_user",
                        new HomeUserInfo(homeUser.getId(), roleAuths)
                    );

        }
    }
}
