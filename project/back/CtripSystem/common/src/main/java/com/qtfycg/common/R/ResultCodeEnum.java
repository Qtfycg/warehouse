package com.qtfycg.common.R;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    /*
    * user模块返回状态码
    * */
    SUCCESS(200, "成功"),
    FAIL(300, "失败"),
    /*
    * 管理员模块返回状态码
    * */
     ADMIN_SUCCESS(400, "成功"),
    ADMIN_FAIL(500, "失败"),
    /*
    * 产品模块返回状态码
    * */
    PRODUCT_SUCCESS(600, "成功"),
    PRODUCT_FAIL(700, "失败")
    ;



    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
