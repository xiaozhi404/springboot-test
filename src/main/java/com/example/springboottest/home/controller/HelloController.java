package com.example.springboottest.home.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


@Api("hello接口管理")
@RestController
@RequestMapping("/home/hello")
public class HelloController {

    private AtomicInteger a = new AtomicInteger(1);

    @ApiOperation("获取hello world字符串")
    @RequestMapping("/world")
    public String hello() {
        a.getAndIncrement();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
        return "hello world";
    }

    public static void main(String[] args) {
//        String s1=new String("he")+new String("llo");
//        String s2=new String("h")+new String("ello");
//        String s3=s1.intern();
//        String s4=s2.intern();
//        System.out.println(s1==s3);
//        System.out.println(s1==s4);

        String s1 = "Hollis";
        String s2 = new String("Hollis");
        String s3 = new String("Hollis").intern();
        System.out.println(s1 == s2);   //false
        System.out.println(s1 == s3);   //true
        new ArrayList<String>();
    }

}
