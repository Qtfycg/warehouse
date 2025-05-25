package com.qtfycg.user.Vo;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class registerVo {

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9].*)(?=.*[a-zA-Z.!@#$%^&*].*)(?=.*[0-9.!@#$%^&*].*).{6,32}$", message = "密码至少包含数字，字母和符号的两种")
    /*密码至少包含数字，字母和符号的其中两种*/
    private String password;

    @NotNull
    @Size(min = 11, max = 11)
    private String tel;

    @Email
    private String email;

    private Integer status;
    @NotNull
    private String code;
}
