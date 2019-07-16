package com.example.springboottest.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.admin.service.ButtonService;
import com.example.springboottest.common.mapper.ButtonMapper;
import com.example.springboottest.common.pojo.Button;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 按钮表，控制到每个菜单的每个按钮 服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Service
public class ButtonServiceImpl extends ServiceImpl<ButtonMapper, Button> implements ButtonService {

}
