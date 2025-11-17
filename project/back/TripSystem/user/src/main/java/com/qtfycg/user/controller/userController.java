package com.qtfycg.user.controller;

import com.qtfycg.user.config.R.R;
import com.qtfycg.user.config.annotation.getPhone.getPhone;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registryVo;
import com.qtfycg.user.service.userService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/user")
@Validated
public class userController {

    @Resource
    userService userService;

    /*
    * 用户登录
    * */
    @PostMapping("/login")
    public R login(@Valid @RequestBody loginVo loginVo, HttpServletResponse response) {
        return userService.login(loginVo, response);
    }

    /*
    * 用户注册
    * */
    @PostMapping("/registry")
    public R registry(@Valid @RequestBody registryVo registryVo) {
        return userService.registry(registryVo);
    }

    /*
    * 获取用户信息
    * */
    @GetMapping("/me")
    public R me(@getPhone String phone) {
        return userService.me(phone);
    }

    /*
    * 获取验证码
    * */
    @GetMapping("/captcha")
    public R captcha(String phone) {
        return userService.getCaptcha(phone);
    }

    /*
    * 添加收货地址
    * */
}
