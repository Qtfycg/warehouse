package com.qtfycg.web.error;

import org.springframework.context.annotation.Configuration;

@Configuration
public class login  extends Exception {
    /*登录失败提示*/
    public String getMessage() {
        return "用户名或密码错误";
    }
}
