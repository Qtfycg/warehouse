package com.qtfycg.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.JWT.jwtUtils;
import com.qtfycg.common.R.R;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registerVo;
import com.qtfycg.user.domain.entity.user;
import com.qtfycg.user.mapper.userMapper;
import com.qtfycg.user.service.userService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService {

    @Resource
    PasswordEncoder passwordEncoder;
    @Autowired
    jwtUtils jwtUtils;
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
}




