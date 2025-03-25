package com.qtfycg.controller;

import com.qtfycg.config.JWT;
import com.qtfycg.entity.account;
import com.qtfycg.service.accountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "账号管理",description = "账号的登录、注册")
public class accountController {
    @Resource
    JWT jwt;
    @Resource
    accountService accountService;

    @Operation(summary = "用户登录", description = "通过' role == user '判断身份")
    @PostMapping("/login/user")
    public String userLogin(@RequestParam("username")String username,@RequestParam("password")String password) {
        account user = accountService.login(username, password, "user");
        if(user != null){
            String token = jwt.generateToken(username);
            return "user login successful. Token: " + token;
        }
        return "Invalid username or password";
    }


    @Operation(summary = "管理员登录", description = "通过' role == admin '判断身份")
    @PostMapping("/login/admin")
    public String adminLogin(@RequestParam("username")String username,@RequestParam("password")String password) {

        account admin = accountService.login(username, password,"admin");
        if (admin != null) {
            String token = jwt.generateToken(username);
            return "Admin login successful. Token: " + token;
        } else {
            return "Invalid username or password";
        }
    }

    @Operation(summary = "用户注册", description = "用户注册")
    @PostMapping("/register/user")
    public String userRegister(@RequestParam("username")String username,@RequestParam("password")String password) {
        account user = accountService.register(username, password,"user");
        if (user != null) {
            return "User register successful";
        } else {
            return "Username already exists";
        }
    }
    @Operation(summary = "管理员注册", description = "用户注册")
    @PostMapping("/register/admin")
    public String adminRegister(@RequestParam("username")String username,@RequestParam("password")String password) {
        account user = accountService.register(username, password,"admin");
        if (user != null) {
            return "User register successful";
        } else {
            return "Username already exists";
        }
    }


}
