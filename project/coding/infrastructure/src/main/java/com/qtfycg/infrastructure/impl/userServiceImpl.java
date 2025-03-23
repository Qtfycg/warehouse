package com.qtfycg.infrastructure.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.application.service.userService;
import com.qtfycg.domain.entity.user;
import com.qtfycg.infrastructure.mapper.userMapper;
import org.springframework.stereotype.Service;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService{

}




