package com.qtfycg.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName(value ="user")
@Data
public class user{
    private Long id;
    private Integer status;
    private String name;
    private String password;
    private String phone;
    private String email;
    private Integer role;

}