package com.example.springboottest.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/admin")
    public String admin() {
        return "admin";
    }


    @RequestMapping("/hello/exception")
    public String helloException() {
        throw new RuntimeException("发送了运行时异常");
    }
}
