package com.qtfycg.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("account")
public class account implements Serializable {

    /**
    * 用户ID
     * -- SETTER --
     *  用户ID

     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 用户名
    */
    private String name;
    /**
    * 密码（加密存储）
    */
    private String password;
    /**
    * 手机号
    */
    private String phone;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 用户角色
    */
    private String role;
    /**
    * 用户状态（0-禁用，1-正常）
    */
    private Integer status;
    /**
    * 注册时间
    */
    private Date createTime;
    /**
    * 最后更新时间
    */
    private Date updateTime;



}
