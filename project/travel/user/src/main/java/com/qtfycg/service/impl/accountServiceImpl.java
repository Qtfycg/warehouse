package com.qtfycg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.entity.account;
import com.qtfycg.mapper.accountMapper;
import com.qtfycg.service.accountService;
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
    @Override
    public account register(String username, String password,String role) {
        QueryWrapper<account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        account user = baseMapper.selectOne(queryWrapper);
        if (user != null) {
            return null;
        } else {
            account account = new account();
            account.setName(username);
            account.setPassword(password);
            account.setRole(role);
            baseMapper.insert(account);
            return account;
        }
    }

    @Override
    public account revise(String username, String password, String role, String phone, String email) {
        return null;
    }

    @Override
    public account delete(String username) {
        return null;
    }

}




