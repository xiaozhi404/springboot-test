package com.example.springboottest.controller;


import com.example.springboottest.service.AsyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private AsyncServiceImpl asyncService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/async/{id}")
    public String asyncM(@PathVariable("id") Long id) {
        try {
            asyncService.asyncMethod(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
