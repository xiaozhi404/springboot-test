package com.example.springboottest.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboottest.admin.domain.outbound.MenuOutbound;
import com.example.springboottest.common.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 资源菜单表 服务类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取菜单树结构
     * @param userId
     * @return
     */
    List<MenuOutbound> getMenuList(Integer userId) throws Exception;
}
