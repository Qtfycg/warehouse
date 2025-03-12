package com.qtfycg.travel.Account.controller;

import com.qtfycg.travel.Account.service.accountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class login {

    @Resource
    accountService accountService;

    /*用户/管理员登录*/
    @GetMapping("/user")
    public String user(String username, String password) {
        boolean user = accountService.login(username, password);
        if (user) {
            return "登陆成功";
        }
        return "登陆失败";
    }
    @RequestMapping("/admin")
    public String admin(String username, String password) {
        return "login";
    }

}
