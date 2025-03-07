package com.qtfycg.api.controller;


import com.qtfycg.service.impl.userServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class login {
    @Resource
    userServiceImpl userServiceImpl;

    /*用户登录*/
    @RequestMapping("/user")
    public String user(String username, String password) {
        return "denglu1";
    }

    /*管理员登陆*/
    @RequestMapping("/admin")
    public String admin(String adminName, String password) {
        return "admin";
    }
}

