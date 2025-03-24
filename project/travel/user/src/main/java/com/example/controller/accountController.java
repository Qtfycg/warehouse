package com.example.controller;

import com.example.config.JWT;
import com.example.entity.account;
import com.example.service.accountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "账号管理",description = "账号的登录、注册")
public class accountController {
    @Resource
    JWT jwt;
    @Resource
    accountService accountService;

    @Operation(summary = "用户登录", description = "通过' role == user '判断身份")
    @PostMapping("/user")
    public String userLogin(String username,String password) {

        account user = accountService.login(username, password,"user");
        if (user != null) {
            String token = jwt.generateToken(username);
            return "User login successful. Token: " + token;
        } else {
            return "Invalid username or password";
        }
    }
    @Operation(summary = "管理员登录", description = "通过' role == admin '判断身份")
    @PostMapping("/admin")
    public String adminLogin(String username,String password) {

        account admin = accountService.login(username, password,"admin");
        if (admin != null) {
            String token = jwt.generateToken(username);
            return "Admin login successful. Token: " + token;
        } else {
            return "Invalid username or password";
        }
    }

    @Operation(summary = "用户注册", description = "管理员账号不支持注册")
    @PostMapping("/register")
    public String register(String username,String password) {
        account user = accountService.register(username, password,"user");
        if (user != null) {
            return "User register successful";
        } else {
            return "Username already exists";
        }
    }
}
