package com.qtfycg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.admin.domain.Vo.loginVo;
import com.qtfycg.admin.domain.entity.user;
import com.qtfycg.common.R.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface adminService extends IService<user> {

    R login(loginVo loginVo, HttpServletResponse response);
    R getInfo(HttpServletRequest request);
    R getList(Integer page, Integer size, String keyword,HttpServletRequest request);
}
