package com.qtfycg.common.utils.R;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private R() {}

    public static R ok() {
        R r = new R();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ResultCodeEnum.FAIL.getCode());
        r.setMessage(ResultCodeEnum.FAIL.getMessage());
        return r;
    }

    public R code(Integer code) {
        this.code = code;
        return this;
    }

    public R message(String message) {
        this.message = message;
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.data.putAll(map);
        return this;
    }
}
