package com.example.springboottest.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.common.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2019-06-28
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
