package com.qtfycg.user.domain.Vo;

import lombok.Data;

@Data
public class registerVo {

    private String name;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    public registerVo() {
    }
}
