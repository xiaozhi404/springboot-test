package com.example.springboottest.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.common.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-08
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
