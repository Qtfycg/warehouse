package com.qtfycg.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.entity.account;
import org.springframework.stereotype.Service;

@Service
public interface accountService extends IService<account> {
    account login(String username, String password,String role);
    account register(String username, String password,String role);

}
