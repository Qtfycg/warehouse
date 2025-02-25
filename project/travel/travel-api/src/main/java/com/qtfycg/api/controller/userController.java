package com.qtfycg.api.controller;

import com.qtfycg.user.service.userService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {
    @Resource
    userService userService;


    /*用户登录*/
    @GetMapping("/login")
    public String login(String username, String password){
        return "login";
    }
}
