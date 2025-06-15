package com.qtfycg.user.domain.Vo;

import lombok.Data;
import lombok.NonNull;

@Data
public class loginVo {

    private String phone;
    private String password;
    private String code; // 验证码
    public loginVo(){

    }
}
