package com.qtfycg.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value ="user")
@Data
public class user {
    private Long id;

    private String name;

    private String password;

    private String phone;

    private String email;

    private Integer status;
}