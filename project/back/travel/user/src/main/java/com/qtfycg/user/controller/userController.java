package com.qtfycg.user.controller;

import com.qtfycg.common.utils.R.R;
import com.qtfycg.user.Vo.loginVo;
import com.qtfycg.user.Vo.registerVo;
import com.qtfycg.user.Vo.updateVo;
import com.qtfycg.user.service.userService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class userController {
    @Resource
    userService userService;

    /*
    * 用户登录
    * */
    @PostMapping("/login")
    public R login(@RequestBody loginVo login) {
        return userService.login(login);
    }

    /*
    * 用户注册
    * */
    @PostMapping("/register")
    public R register(@RequestBody registerVo register) {
        return userService.register(register);
    }

    /*
    * 用户信息修改
    * */
    @PutMapping("/update")
    public R update(@RequestBody updateVo update) {
            return userService.updateUser(update);
    }

    /*
    * 获取当前用户信息
    * */
    @GetMapping("/info")
    public R getInfo(@RequestParam("tel") String tel) {
        return userService.getInfo(tel);
    }

    /*
    * 验证码发送
    * */
    @PostMapping("/code")
    public R sendCode(@RequestParam("tel") String tel) {
        return userService.sendCode(tel);
    }
}
