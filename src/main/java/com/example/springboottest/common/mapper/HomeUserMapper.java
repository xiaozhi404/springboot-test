package com.example.springboottest.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.common.pojo.HomeUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 前台用户 Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-09
 */
@Mapper
public interface HomeUserMapper extends BaseMapper<HomeUser> {

}
