package com.qtfycg.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.user.entity.user;
import com.qtfycg.user.mapper.userMapper;
import com.qtfycg.user.service.userService;
import org.springframework.stereotype.Service;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService{

}




