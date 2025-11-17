package com.qtfycg.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtfycg.user.config.R.R;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registryVo;
import com.qtfycg.user.domain.entity.user;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface userService extends IService<user> {
    R login(loginVo loginVo, HttpServletResponse response);
    R registry(registryVo registryVo);
    R me(String phone);
    R getCaptcha(String phone);
}
