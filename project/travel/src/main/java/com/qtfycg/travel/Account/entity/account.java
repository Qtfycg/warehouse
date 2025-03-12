package com.qtfycg.travel.Account.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class account {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String role;
    private String status;

    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();


}
