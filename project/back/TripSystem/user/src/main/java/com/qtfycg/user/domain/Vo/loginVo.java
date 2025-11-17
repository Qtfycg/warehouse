package com.qtfycg.user.domain.Vo;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class loginVo {

    @Pattern(regexp = "^\\+?\\d{6,20}$", message="手机号格式不正确")
    private String phone;
    private String password;
    private String code;
}
