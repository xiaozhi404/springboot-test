package com.example.springboottest.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottest.mapper.UserMapper;
import com.example.springboottest.pojo.User;
import com.example.springboottest.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2019-06-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
