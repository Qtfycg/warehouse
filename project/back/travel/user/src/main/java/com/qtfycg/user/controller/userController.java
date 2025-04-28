package com.qtfycg.user.controller;

import com.qtfycg.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class userController {

    /*密码登录*/
    @RequestMapping("login")
    public R login(String tel , String pass
                   word) {

        return R.ok().message("登录成功");
    }

}
