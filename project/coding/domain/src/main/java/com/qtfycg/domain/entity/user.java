package com.qtfycg.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class user {
    @TableId
    private int id;
    private String name;
    private String password;
    private String nickname;
    private String avatar;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
