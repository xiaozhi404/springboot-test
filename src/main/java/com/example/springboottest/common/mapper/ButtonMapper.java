package com.example.springboottest.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.common.pojo.Button;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ButtonMapper extends BaseMapper<Button> {

}
