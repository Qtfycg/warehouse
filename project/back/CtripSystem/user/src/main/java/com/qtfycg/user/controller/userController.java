package com.qtfycg.user.controller;

import com.qtfycg.common.R.R;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registerVo;
import com.qtfycg.user.service.userService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class userController {
    @Resource
    userService userService;

    /*
    * 用户注册接口
    * */
    @PostMapping("/register")
    public R register(@RequestBody registerVo registerVo) {
        return userService.register(registerVo);
    }

    /*
    * 用户登录接口
    * */
    @PostMapping("/login")
    public R login(@RequestBody loginVo loginVo) {
        return userService.login(loginVo);
    }

}
