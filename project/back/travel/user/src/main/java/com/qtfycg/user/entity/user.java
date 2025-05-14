package com.qtfycg.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class user implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @NotNull
    @TableField("tel")
    private String tel;

    @TableField("email")
    private String email;

    @TableField("status")
    private Integer status;

    public user() {

    }
}
