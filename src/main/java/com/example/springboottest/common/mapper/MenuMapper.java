package com.example.springboottest.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.common.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 资源菜单表 Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
