package com.qtfycg.common.back;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    /*user*/
    Login_Success(200,"用户登陆成功"),
    Login_Fail(500,"账号或密码错误");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
