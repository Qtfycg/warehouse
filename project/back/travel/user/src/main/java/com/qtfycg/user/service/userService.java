package com.qtfycg.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.user.entity.user;


public interface userService extends IService<user> {

    /*
     * 用户登录（根据手机号和密码登录）
     * */
    user login(String tel, String password);
    /*
    * 用户注册
    * */
    boolean register(user user);
    /*
    * 用户信息修改
    * */
    boolean updateUser(user user);
    /*
    * 获取用户信息
    * */
    user getInfo(String tel);

}
