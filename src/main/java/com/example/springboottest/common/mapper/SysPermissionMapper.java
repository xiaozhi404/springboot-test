package com.example.springboottest.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.common.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

}
