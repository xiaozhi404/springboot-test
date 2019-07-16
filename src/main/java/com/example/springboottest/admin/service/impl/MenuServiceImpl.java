package com.example.springboottest.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.admin.domain.constant.PermisionTypeEnum;
import com.example.springboottest.admin.domain.outbound.MenuOutbound;
import com.example.springboottest.admin.service.MenuService;
import com.example.springboottest.admin.service.SysPermissionService;
import com.example.springboottest.admin.service.SysRolePermisionService;
import com.example.springboottest.admin.service.SysRoleUserService;
import com.example.springboottest.common.converter.CommonConverter;
import com.example.springboottest.common.mapper.MenuMapper;
import com.example.springboottest.common.pojo.Menu;
import com.example.springboottest.common.pojo.SysPermission;
import com.example.springboottest.common.pojo.SysRolePermision;
import com.example.springboottest.common.pojo.SysRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源菜单表 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysRolePermisionService sysRolePermisionService;
    @Autowired
    private SysPermissionService sysPermissionService;


    @Override
    public List<MenuOutbound> getMenuList(Integer userId) throws Exception {
        List<Integer> roleIds = sysRoleUserService.list(new QueryWrapper<SysRoleUser>().eq("sys_user_id", userId)).stream().map(SysRoleUser::getSysRoleId).collect(Collectors.toList());
        List<Integer> permisionIds = sysRolePermisionService.list(new QueryWrapper<SysRolePermision>().in("sys_role_id", roleIds)).stream().map(SysRolePermision::getSysPemisionId).collect(Collectors.toList());
        List<Integer> resourceIds = sysPermissionService.list(new QueryWrapper<SysPermission>().in("id", permisionIds).eq("permission_type", PermisionTypeEnum.MENU)).stream().map(SysPermission::getResourcesId).collect(Collectors.toList());
        List<Menu> menus = (List<Menu>) this.listByIds(resourceIds);
        List<MenuOutbound> menuOutbounds = CommonConverter.convertPoListToVOList(menus, MenuOutbound.class);
        return buildTree(menuOutbounds);
    }


    public List<MenuOutbound> buildTree(List<MenuOutbound> treeNodes) {
        List<MenuOutbound> trees = new ArrayList<>();
        for (MenuOutbound treeNode : treeNodes) {
            if (0 == treeNode.getParentId()) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    public MenuOutbound findChildren(MenuOutbound treeNode,List<MenuOutbound> treeNodes) {
        for (MenuOutbound it : treeNodes) {
            if(treeNode.getId() == it.getParentId()) {
                if (treeNode.getSubMenus() == null) {
                    treeNode.setSubMenus(new ArrayList<>());
                }
                treeNode.getSubMenus().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}
