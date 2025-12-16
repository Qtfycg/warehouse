package com.qtfycg.gateway.Auth.Jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class jwtPayload {

    /*
    * 登录账号
    * */
    private String identifier;
    /*
    * 角色
    * */
    private String role;
    /*
    * 所属项目
    * */
    private Integer origin;
}
