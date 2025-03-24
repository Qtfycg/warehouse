package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("account")
public class account {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String role;
    private Integer status;


    LocalDate createTime = LocalDate.now();
    LocalDate updateTime = LocalDate.now();

}
