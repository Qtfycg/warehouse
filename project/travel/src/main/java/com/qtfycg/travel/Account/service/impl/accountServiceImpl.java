package com.qtfycg.travel.Account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.travel.Account.entity.account;

import com.qtfycg.travel.Account.mapper.accountMapper;
import com.qtfycg.travel.Account.service.accountService;
import org.springframework.stereotype.Service;


@Service
public class accountServiceImpl extends ServiceImpl<accountMapper, account> implements accountService {

    @Override
    public boolean login(String username, String password) {
        QueryWrapper<account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);

        account userAccount = this.getOne(queryWrapper);
        // 如果用户存在且密码匹配，则返回 true，否则返回 false
        return userAccount != null && userAccount.getPassword().equals(password);

    }
}




