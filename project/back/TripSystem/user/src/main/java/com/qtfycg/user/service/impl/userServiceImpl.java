package com.qtfycg.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.user.config.JWT.JwtUtil;
import com.qtfycg.user.config.R.R;
import com.qtfycg.user.config.security.Captcha;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registryVo;
import com.qtfycg.user.domain.entity.user;
import com.qtfycg.user.mapper.userMapper;
import com.qtfycg.user.service.userService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService{

    @Resource
    JwtUtil jwtUtil;
    @Resource(name = "stringRedisTemplate")
    StringRedisTemplate redisTemplate;

    /*
    * 用户登录
    * * */
    @Override
    public R login(loginVo loginVo, HttpServletResponse response) {
        user newUser = this.baseMapper.selectOne(new QueryWrapper<user>().eq("phone", loginVo.getPhone()));
        if (newUser == null) {
            return R.error().code(300).message("用户不存在");
        }

        if (!new BCryptPasswordEncoder().matches(loginVo.getPassword(), newUser.getPassword())) {
            return R.error().code(301).message("密码错误");
        }
        String token = jwtUtil.generateToken(newUser.getPhone(), String.valueOf(newUser.getRole()));
        response.setHeader("Authorization", "Bearer " + token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return R.ok()
                .code(200)
                .message("登录成功")
                .data("token", token)
                .data("user", newUser);
    }

    @Override
    public R registry(registryVo registryVo) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", registryVo.getPhone());
        queryWrapper.eq("name", registryVo.getName());
        user existingUser = this.baseMapper.selectOne(queryWrapper);
        if (existingUser != null) {
            return R.error()
                    .code(302)
                    .message("用户已存在")
                    .data("user", existingUser);
        }
        user newUser = new user();
        newUser.setName(registryVo.getName());
        newUser.setPhone(registryVo.getPhone());
        newUser.setEmail(registryVo.getEmail());
        newUser.setAvatar(registryVo.getAvatar());
        String encodedPassword = new BCryptPasswordEncoder().encode(registryVo.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setStatus(1);
        newUser.setRole(1);
        this.baseMapper.insert(newUser);
        return R.ok()
                .code(200)
                .message("注册成功")
                .data("user", newUser);
    }

    @Override
    public R me(String phone) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        user existingUser = this.baseMapper.selectOne(queryWrapper);
        if (existingUser != null) {
            return R.ok()
                    .code(200)
                    .message("用户信息获取成功")
                    .data("user", existingUser);
        }
        return R.error()
                .code(303)
                .message("用户不存在");
    }

    @Override
    public R getCaptcha(String phone) {
        try {
            Captcha.CaptchaResult captchaResult = Captcha.toBase64();
            // 将验证码存入 Redis，设置过期时间为 5 分钟
            redisTemplate.opsForValue().set("captcha:" + phone, captchaResult.code, 5, TimeUnit.MINUTES);
            return R.ok()
                    .code(200)
                    .message("验证码获取成功")
                    .data("captcha", captchaResult.base64Image);
        } catch (Exception e) {
            return R.error()
                    .code(500)
                    .message("验证码获取失败");
        }
    }


}
