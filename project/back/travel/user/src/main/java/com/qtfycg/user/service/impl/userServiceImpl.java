package com.qtfycg.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.utils.Jwt.Jwt;
import com.qtfycg.common.utils.R.R;
import com.qtfycg.user.Vo.loginVo;
import com.qtfycg.user.Vo.registerVo;
import com.qtfycg.user.Vo.updateVo;
import com.qtfycg.user.entity.user;
import com.qtfycg.user.mapper.userMapper;
import com.qtfycg.user.service.userService;
import com.qtfycg.user.utils.PasswordUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService{

    @Resource
    RedisTemplate<String, String> redisTemplate;

    /*
    * 用户登录
    * */
    @Override
    public R login(loginVo login) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        String tel = login.getTel();
        queryWrapper.eq("tel",tel);
        user existingUser = this.getOne(queryWrapper);
        if (existingUser != null && PasswordUtil.matches(login.getPassword(), existingUser.getPassword())){

            String key = "captcha:" + tel;
            String code = redisTemplate.opsForValue().get(key);
            if (code == null) {
                return R.error().message("验证码已失效");
            }
            String token = Jwt.getToken(tel);
            return R.ok().message("登录成功").data("token", token).data("user", existingUser);
        }
        return R.error().message("用户名或密码错误");
    }

    /*
    * 用户注册
    * */
    @Override
    public R register(registerVo register) {
        // 检查手机号是否已存在
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", register.getTel());
        user existingUser = this.getOne(queryWrapper);
        if(existingUser != null) {
            return R.error().message("手机号已存在,请更换手机号");
        }else {
            user newUser = new user();
            newUser.setTel(register.getTel());
            String encryptedPassword = PasswordUtil.encode(register.getPassword());
            newUser.setPassword(encryptedPassword);
            newUser.setName(register.getName());
            newUser.setEmail(register.getEmail());
            newUser.setStatus(1);
            this.save(newUser);
            return R.ok().message("注册成功").data("user", newUser);
        }
    }

    /*
    * 修改用户信息
    * */
    @Override
    public R updateUser(updateVo update) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", update.getTel());
        user existingUser = this.getOne(queryWrapper);
        if(existingUser != null){
            existingUser.setName(update.getName());
            String encryptedPassword = PasswordUtil.encode(update.getPassword());
            existingUser.setPassword(encryptedPassword);
            existingUser.setEmail(update.getEmail());
            this.updateById(existingUser);
            return R.ok().message("用户信息修改成功").data("user", existingUser);
        }
        return R.error().message("用户不存在");
    }

    /*
    * 获取用户信息
    * */
    @Override
    public R getInfo(String tel) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", tel);
        user existingUser = this.getOne(queryWrapper);
        if (existingUser == null) {
            return R.error().message("用户不存在");
        }
        return R.ok().message("获取用户信息成功").data("user", existingUser);
    }

    @Override
    public R sendCode(String tel) {
        SpecCaptcha captcha = new SpecCaptcha(130, 48, 4);
        captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        String result = captcha.text();
        redisTemplate.opsForValue().set("captcha:" + tel, result, 5, TimeUnit.MINUTES);
        String base64 = captcha.toBase64();
        return R.ok().message("验证码已发送")
                .data("uuid", tel)
                .data("img", base64);
    }

}




