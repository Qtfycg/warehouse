package com.qtfycg.web.exception;

public class login extends Exception{
    public login(String message) {
        super("密码错误，登陆失败");
    }

    }
