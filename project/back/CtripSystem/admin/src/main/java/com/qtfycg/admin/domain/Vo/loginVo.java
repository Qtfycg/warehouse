package com.qtfycg.admin.domain.Vo;

import lombok.Data;
@Data
public class loginVo {
    private String phone;
    private String password;
    private String code; // 验证码
    public loginVo(){

    }
}
