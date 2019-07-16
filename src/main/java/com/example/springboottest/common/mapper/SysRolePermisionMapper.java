package com.example.springboottest.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.common.pojo.SysRolePermision;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统角色-权限关联表 Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Mapper
public interface SysRolePermisionMapper extends BaseMapper<SysRolePermision> {

}
