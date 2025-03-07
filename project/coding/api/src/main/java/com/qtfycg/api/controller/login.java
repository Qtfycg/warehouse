package com.qtfycg.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class login {
    @RequestMapping("/user")
    public String userLogin(String username, String password,String code) {
        return "login";
    }

    @RequestMapping("/admin")
    public String adminLogin(String username, String password,String code) {
        return "login";
    }
}
