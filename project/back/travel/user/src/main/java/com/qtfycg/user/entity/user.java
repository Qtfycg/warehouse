package com.qtfycg.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class user implements Serializable {
    @NotNull
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private String password;

    private String phone;

    private String email;

    private Integer status;



}
