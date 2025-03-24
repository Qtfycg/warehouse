package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.account;
import com.example.mapper.accountMapper;
import com.example.service.accountService;
import org.springframework.stereotype.Service;


@Service
public class accountServiceImpl extends ServiceImpl<accountMapper, account> implements accountService {

    /*登录*/
    @Override
    public account login(String username, String password,String role) {
        QueryWrapper<account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        queryWrapper.eq("password", password);
        queryWrapper.eq("role", role);
        return  baseMapper.selectOne(queryWrapper);
    }

    /*注册*/



}




