package com.qtfycg.user.domain.Vo;

import lombok.Data;
import lombok.NonNull;

@Data
public class loginVo {
    @NonNull
    private String phone;
    private String password;
    private String code; // 验证码
}
