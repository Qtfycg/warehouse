package com.qtfycg.travel.Account.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.travel.Account.entity.account;


public interface accountService extends IService<account> {
    boolean login(String username, String password);

}
