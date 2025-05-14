package com.qtfycg.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.user.Vo.loginVo;
import com.qtfycg.user.Vo.registerVo;
import com.qtfycg.user.Vo.updateVo;
import com.qtfycg.user.entity.user;

public interface userService extends IService<user> {

    /*
     * 用户登录（根据手机号和密码登录）
     * */
    user login(loginVo login);
    /*
    * 用户注册
    * */
    boolean register(registerVo register);
    /*
    * 用户信息修改
    * */
    boolean updateUser(updateVo update);
    /*
    * 获取用户信息
    * */
    user getInfo(String tel);

}
