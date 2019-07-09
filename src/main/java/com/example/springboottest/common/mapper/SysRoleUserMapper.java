package com.example.springboottest.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.common.pojo.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 系统角色-用户关联表 Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-08
 */
@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

}
