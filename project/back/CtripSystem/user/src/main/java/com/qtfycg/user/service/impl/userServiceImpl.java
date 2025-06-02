package com.qtfycg.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.JWT.jwtUtils;
import com.qtfycg.common.R.R;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.user.config.Redis;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registerVo;
import com.qtfycg.user.domain.entity.user;
import com.qtfycg.user.mapper.userMapper;
import com.qtfycg.user.service.userService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService {

    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    jwtUtils jwtUtils;
    @Resource
    Redis redis;
    @Override
    public R register(registerVo registerVo) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", registerVo.getPhone());
        user existingUser = baseMapper.selectOne(queryWrapper);
        if (existingUser != null) {
            return R.error().message("手机号已被注册");
        }else {
            user newUser = new user();
            /*
            * 利用雪花算法生成唯一ID
            * */
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
            long id = idGenerator.nextId();
            newUser.setId(id);
            newUser.setPhone(registerVo.getPhone());
            String encodedPassword = passwordEncoder.encode(registerVo.getPassword());
            newUser.setPassword(encodedPassword);
            newUser.setName(registerVo.getName());
            newUser.setEmail(registerVo.getEmail());
            newUser.setStatus(1); // 默认状态为1
            baseMapper.insert(newUser);
            return R.ok()
                    .code(200).
                    message("注册成功").
                    data("user", newUser);
        }
    }

    @Override
    public R login(loginVo loginVo) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", loginVo.getPhone());
        user existingUser = baseMapper.selectOne(queryWrapper);
        if(existingUser != null){
            if(passwordEncoder.matches(loginVo.getPassword(), existingUser.getPassword())){
                String token = jwtUtils.generateToken(existingUser.getId(), existingUser.getPhone());
                return R.ok()
                        .code(200)
                        .message("登录成功")
                        .data("token", token)
                        .data("user", existingUser);
            }else {
                return R.error().message("密码错误");
            }
        }else {
            return R.error().message("用户不存在");
        }
    }

    @Override
    public R captcha(String phone) throws IOException, FontFormatException {
        /*
        * 验证码生成
        * */
        SpecCaptcha captcha = new SpecCaptcha(130, 48,4);
        captcha.setCharType(SpecCaptcha.TYPE_ONLY_NUMBER);// 设置验证码类型为纯数字
        String captchaText = captcha.text();// 获取验证码文本
        captcha.setFont(Captcha.FONT_1); // 设置字体
        /*
        * 存入redis
        * */
        redis.redisTemplate().opsForValue().set("captcha:" + phone, captchaText, 5 * 60); // 设置验证码有效期为5分钟


        return R.ok().data("captcha", captchaText)
                .data("phone", phone)
                .message("验证码生成成功");
    }
}




