package com.qtfycg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.entity.domain.user;
import com.qtfycg.entity.mapper.userMapper;
import com.qtfycg.service.userService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService{

    @Resource
    userMapper userMapper;
    @Override
    public user login(String username, String password) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        queryWrapper.eq("password", password);


        return userMapper.login(username, password);
    }
}




