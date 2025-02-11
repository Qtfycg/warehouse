package com.qtfycg.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.back.domain.admin;
import com.qtfycg.back.mapper.adminMapper;
import com.qtfycg.back.service.adminService;
import org.springframework.stereotype.Service;

@Service
public class adminServiceImpl<adminName, adminPassword> extends ServiceImpl<adminMapper, admin> implements adminService{

}




