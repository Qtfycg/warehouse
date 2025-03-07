package com.qtfycg.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.entity.domain.user;


public interface userService extends IService<user> {
    public user login(String username, String password);

}
