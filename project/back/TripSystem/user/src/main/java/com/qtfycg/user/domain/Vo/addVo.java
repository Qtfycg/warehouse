package com.qtfycg.user.domain.Vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class addVo {

    @Pattern(regexp = "^\\+?\\d{6,20}$", message="手机号格式不正确")
    private String tel;
    @NotBlank(message="用户名不能为空")
    private String names;
    private String sex;
    private int age;
    @Pattern(regexp = "^[1-9]\\\\d{5}(18|19|([23]\\\\d))\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}[0-9Xx]$")
    private String identityCard;

}
