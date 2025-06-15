package com.qtfycg.user.controller;

import com.qtfycg.common.R.R;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registerVo;
import com.qtfycg.user.domain.Vo.updateVo;
import com.qtfycg.user.service.userService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;

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
    public R login(@RequestBody loginVo loginVo, HttpServletResponse response) {
        return userService.login(loginVo, response);
    }

    /*
    * 验证码接口
    * */
    @GetMapping("/captcha")
    public R captcha(@RequestParam("phone") String phone) throws IOException, FontFormatException {
        return userService.captcha(phone);
    }

    /*
    * 获取用户个人信息接口
    * */
    @GetMapping("/me")
    public R getInfo() {
        return userService.getInfo();
    }

    /*
    * 修改用户信息
    * */
    @PutMapping("/update")
    public R updateUser(@RequestBody updateVo updateVo) {
        return userService.updateInfo(updateVo);
    }

}
