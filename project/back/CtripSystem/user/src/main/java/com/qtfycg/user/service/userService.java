package com.qtfycg.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.common.R.R;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registerVo;
import com.qtfycg.user.domain.entity.user;


public interface userService extends IService<user> {

    R register(registerVo registerVo);
    R login(loginVo loginVo);
}
