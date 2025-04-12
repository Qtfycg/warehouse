package com.qtfycg.common.back;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private Integer code;  // 状态码
    private String message; // 返回消息
    private Map<String, Object> data = new HashMap<>(); // 返回数据
    private R() {}

    // 用户登录成功
    public static R ok() {
        R r = new R();
        r.setCode(ResultCodeEnum.Login_Success.getCode());
        r.setMessage(ResultCodeEnum.Login_Success.getMessage());
        return r;
    }

    // 用户登录失败
    public static R error() {
        R r = new R();
        r.setCode(ResultCodeEnum.Login_Fail.getCode());
        r.setMessage(ResultCodeEnum.Login_Success.getMessage());
        return r;
    }

    // 自定义状态码和消息
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
