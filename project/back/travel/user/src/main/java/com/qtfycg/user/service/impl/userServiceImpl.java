package com.qtfycg.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.user.entity.user;
import com.qtfycg.user.mapper.userMapper;
import com.qtfycg.user.service.userService;
import org.springframework.stereotype.Service;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService{


    /*
    * 用户登录
    * */
    @Override
    public user login(String tel, String password) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel",tel);
        queryWrapper.eq("password",password);
        return this.getOne(queryWrapper);
    }

    /*
    * 用户注册
    * */
    @Override
    public boolean register(user user) {
        // 检查手机号是否已存在
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", user.getTel());
        user existingUser = this.getOne(queryWrapper);
        if(existingUser != null) {
            return false;
        }else {
            return this.save(user);
        }
    }

    /*
    * 修改用户信息
    * */
    @Override
    public boolean updateUser(user user) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", user.getTel());
        user existingUser = this.getOne(queryWrapper);
        if(existingUser != null){
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            return this.updateById(existingUser);
        }
        return false;
    }


    /*
    * 获取用户信息
    * */
    @Override
    public user getInfo(String tel) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", tel);
        return this.getOne(queryWrapper);
    }



}




