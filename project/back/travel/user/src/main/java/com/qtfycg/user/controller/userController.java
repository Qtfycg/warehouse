package com.qtfycg.user.controller;

import com.qtfycg.common.utils.Jwt.Jwt;
import com.qtfycg.common.utils.R.R;
import com.qtfycg.user.Vo.loginVo;
import com.qtfycg.user.Vo.registerVo;
import com.qtfycg.user.Vo.updateVo;
import com.qtfycg.user.entity.user;
import com.qtfycg.user.service.userService;
import com.qtfycg.user.utils.PasswordUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class userController {
    @Resource
    userService userService;

    @PostMapping("/login")
    public R login(@RequestBody loginVo login) {

        user user = userService.login(login);
        if (user != null && PasswordUtil.matches(login.getPassword(), user.getPassword())) {
            // 生成token
            String tel = login.getTel();
            String token = Jwt.getToken(tel);
            return R.ok().message("登陆成功").data("token", token);
        } else {
            return R.error().message("登录失败，用户名或密码错误");
        }
    }



    /*
    * 用户注册
    * */
    @RequestMapping("/register")
    public R register(@RequestBody registerVo register) {
        boolean result = userService.register(register);
        if (result) {
            return R.ok().message("注册成功").data("user", register);
        } else {
            return R.error().message("注册失败，用户已存在");
        }
    }

    /*
    * 用户信息修改
    * */
    @RequestMapping("/update")
    public R update(@RequestBody updateVo update) {
        boolean result = userService.updateUser(update);
        if (result) {
            return R.ok().message("修改成功").data("user", update);
        } else {
            return R.error().message("与当前信息一致，修改失败");
        }
    }
    /*
    * 获取当前用户信息
    * */
    @PostMapping("/info")
    public R getInfo(@RequestParam("tel") String tel) {
        user user = userService.getInfo(tel);
        if (user == null) {
            return R.error().message("获取失败，用户不存在");
        } else {
            return R.ok().message("获取成功").data("user", user);
        }
    }
}
