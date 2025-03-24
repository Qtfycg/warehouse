package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.account;
import org.springframework.stereotype.Service;

@Service
public interface accountService extends IService<account> {
    account login(String username, String password,String role);

}
