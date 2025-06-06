package com.qtfycg.common.utils.R;

import lombok.Getter;

public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(300, "失败"),




    VALIDATE_ERROR(400, "参数校验失败"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "没有权限"),
    NOT_FOUND(404, "资源不存在"),
    TOO_MANY_REQUEST(429, "请求频繁"),
    SERVER_ERROR(500, "服务器内部错误");

    @Getter
    private final Integer code;
    @Getter
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
