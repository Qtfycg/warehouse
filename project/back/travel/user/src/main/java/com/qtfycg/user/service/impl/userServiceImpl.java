package com.qtfycg.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.user.Vo.loginVo;
import com.qtfycg.user.Vo.registerVo;
import com.qtfycg.user.Vo.updateVo;
import com.qtfycg.user.entity.user;
import com.qtfycg.user.mapper.userMapper;
import com.qtfycg.user.service.userService;
import com.qtfycg.user.utils.PasswordUtil;
import org.springframework.stereotype.Service;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService{


    /*
    * 用户登录
    * */
    @Override
    public user login(loginVo login) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        String tel = login.getTel();
        queryWrapper.eq("tel",tel);
        return this.getOne(queryWrapper);
    }

    /*
    * 用户注册
    * */
    @Override
    public boolean register(registerVo register) {
        // 检查手机号是否已存在
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", register.getTel());
        user existingUser = this.getOne(queryWrapper);
        if(existingUser != null) {
            return false;
        }else {
            user newUser = new user();
            newUser.setTel(register.getTel());
            String encryptedPassword = PasswordUtil.encode(register.getPassword());
            newUser.setPassword(encryptedPassword);
            newUser.setName(register.getName());
            newUser.setEmail(register.getEmail());
            newUser.setStatus(1);
            return this.save(newUser);
        }
    }

    /*
    * 修改用户信息
    * */
    @Override
    public boolean updateUser(updateVo update) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", update.getTel());
        user existingUser = this.getOne(queryWrapper);
        if(existingUser != null){
            existingUser.setName(update.getName());
            String encryptedPassword = PasswordUtil.encode(update.getPassword());
            existingUser.setPassword(encryptedPassword);
            existingUser.setEmail(update.getEmail());
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




