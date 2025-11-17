package com.qtfycg.user.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class user {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private int status;
    private int role;
    private String avatar;
    private String names;
    private String sex;
    private int age;
    private String tel;
    private String identityCard;




}
