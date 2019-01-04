package com.example.springboottest.service;

import com.example.springboottest.pojo.User;
import com.example.springboottest.task.PrintTask;
import com.example.springboottest.tkMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;

@Service
public class AsyncServiceImpl {

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private UserMapper userMapper;

    @Async
    public void asyncMethod(Long id) throws Exception {

        User user = new User();
        user.setId(id);
        user.setPassword("1");
        userMapper.updateByPrimaryKeySelective(user);
        CompletionService completionService = new ExecutorCompletionService(executor);
        for (int i = 0; i < 100; ++i) {
            completionService.submit(new PrintTask(), null);
        }
        for (int i = 0; i < 100; ++i) {
            completionService.take().get();
        }
        User user2 = new User();
        user2.setId(id);
        user2.setPassword("0");
        userMapper.updateByPrimaryKeySelective(user2);
        System.out.println("hello");
    }
}
