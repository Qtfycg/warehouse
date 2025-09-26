package com.qtfycg.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.JWT.jwtUtils;
import com.qtfycg.common.R.R;
import com.qtfycg.common.Redis.redisUtils;
import com.qtfycg.common.SnowflakeId.SnowflakeIdGenerator;
import com.qtfycg.common.annotation.Login;
import com.qtfycg.common.annotation.context.loginHolder;
import com.qtfycg.common.mq.Sender.FanoutMessageSender;
import com.qtfycg.common.mq.message.userRegistry;
import com.qtfycg.user.domain.Vo.loginVo;
import com.qtfycg.user.domain.Vo.registerVo;
import com.qtfycg.user.domain.Vo.updateVo;
import com.qtfycg.user.domain.entity.user;
import com.qtfycg.user.feign.fileFeign;
import com.qtfycg.user.mapper.userMapper;
import com.qtfycg.user.service.userService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService {

    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    jwtUtils jwtUtils;
    @Resource
    redisUtils redis;
    @Resource
    FanoutMessageSender fanoutMessageSender;
    @Resource
    fileFeign fileFeign;





    /*
    * 注册接口
    * */
    @Override
    public R register(registerVo registerVo) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", registerVo.getPhone());
        user existingUser = baseMapper.selectOne(queryWrapper);
        if (existingUser != null) {
            return R.error()
                    .code(302)
                    .message("手机号已被注册");
        }else {
            user newUser = new user();
            /*
            * 利用雪花算法生成唯一ID
            * */
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
            long id = idGenerator.nextId();
            newUser.setId(id);
            newUser.setRole(1); // 默认角色为1
            newUser.setPhone(registerVo.getPhone());
            String encodedPassword = passwordEncoder.encode(registerVo.getPassword());
            newUser.setPassword(encodedPassword);
            newUser.setName(registerVo.getName());
            newUser.setEmail(registerVo.getEmail());
            newUser.setAvatar( "https://travel-my.oss-cn-beijing.aliyuncs.com/null5193b2fc-e4ca-420f-ba90-740fc90716df.png"); // 默认头像
            newUser.setStatus(1); // 默认状态为1
            baseMapper.insert(newUser);
            userRegistry userRegisterMessage = new userRegistry();
            userRegisterMessage.setUserId(newUser.getId());
            userRegisterMessage.setPhone(newUser.getPhone());
            userRegisterMessage.setUsername(newUser.getName());
            userRegisterMessage.setEmail(newUser.getEmail());
            fanoutMessageSender.send("user.register.exchange", userRegisterMessage);
            return R.ok()
                    .code(200)
                    .message("注册成功").
                    data("user", newUser);
        }
    }

    /*
    * 登录接口
    * */
    @Override
    public R login(loginVo loginVo , HttpServletResponse response) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", loginVo.getPhone());
        user existingUser = baseMapper.selectOne(queryWrapper);
        if(existingUser != null){
            if(passwordEncoder.matches(loginVo.getPassword(), existingUser.getPassword())){
                String code = Objects.requireNonNull(redis.redisTemplate().opsForValue().get("captcha:" + loginVo.getPhone())).toString();
                if(code == null || !code.equals(loginVo.getCode())) {
                    System.out.println(loginVo.getCode() + " " + code);
                    return R.error()
                            .code(304)
                            .message("验证码错误或已过期");
                }else {
                    String token = jwtUtils.generateToken(existingUser.getId(), existingUser.getPhone());
                    response.setHeader("Authorization", "Bearer " + token);
                    response.setHeader("Access-Control-Expose-Headers", "Authorization"); // 允许前端获取该响应头

                    return R.ok()
                            .code(200)
                            .message("登录成功")
                            .data("token", token)
                            .data("user", existingUser);
                }
            }else {
                return R.error()
                        .code(304)
                        .message("密码错误");
            }
        }else {
            return R.error()
                    .code(303)
                    .message("用户不存在");
        }
    }

    /*
    * 验证码生成接口
    * */
    @Override
    public R captcha(String phone) throws IOException, FontFormatException {
        /*
        * 验证码生成
        * */
        SpecCaptcha captcha = new SpecCaptcha(130, 48,4);
        captcha.setCharType(SpecCaptcha.TYPE_ONLY_NUMBER);// 设置验证码类型为纯数字
        String captchaText = captcha.text();// 获取验证码文本
        /*
         * 存入redis
         * */
        redis.redisTemplate.opsForValue().set("captcha:" + phone, captchaText, 5, TimeUnit.MINUTES);

        captcha.setFont(Captcha.FONT_1); // 设置字体
        /*
        * 将验证码图像输出为Base64编码的字符串
        * */
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        /*
        * 输出验证码图像到字节数组输出流
        * */
        captcha.out(out);

        String base64Img = "data:image/png;base64," + Base64.getEncoder().encodeToString(out.toByteArray());

        return R.ok()
                .code(200)
                .data("phone", phone)
                .data("IMG", base64Img)
                .message("验证码生成成功");
    }

    /*
    * 用户个人信息查询接口
    * */
    @Login
    @Override
    public R getInfo() {
        Long userId = loginHolder.getUserId();
        user userInfo = baseMapper.selectById(userId);
        if (userInfo == null) {
            return R.error()
                    .code(303)
                    .message("用户不存在");
        }
        return R.ok()
                .code(200)
                .data("user", userInfo);
        }

    /*
     * 更新个人信息接口
     * */
    @Login
    @Override
    public R updateInfo(updateVo updateVo) {
        Long userId = loginHolder.getUserId();
        user existingUser = baseMapper.selectById(userId);
        if (existingUser == null) {
            return R.error()
                    .code(303)
                    .message("用户不存在");
        } else {
            existingUser.setName(updateVo.getName());
            existingUser.setEmail(updateVo.getEmail());
            existingUser.setPhone(updateVo.getPhone());
            existingUser.setUsername(updateVo.getUsername());
            existingUser.setAddress(updateVo.getAddress());
            baseMapper.updateById(existingUser);
            return R.ok()
                    .code(200)
                    .message("个人信息更新成功")
                    .data("user", existingUser);
        }
    }

    @Login
    @Override
    public R updateAvatar(MultipartFile avatar) throws Exception{
        Long userId = loginHolder.getUserId();
        user existingUser = baseMapper.selectById(userId);
        if (existingUser == null) {
            return R.error()
                    .code(303)
                    .message("用户不存在");
        }else {
            if (avatar == null || avatar.isEmpty()) {
                return R.error()
                        .code(400)
                        .message("上传的头像不能为空");
            }
            R uploadResult = fileFeign.upload(avatar);
            if (uploadResult.getCode() != 1000) {
                return R.error()
                        .code(uploadResult.getCode())
                        .message(uploadResult.getMessage());
            }
            String avatarUrl = uploadResult.getData().get("url").toString();
            existingUser.setAvatar(avatarUrl);
            baseMapper.updateById(existingUser);
            return R.ok()
                    .code(200)
                    .message("头像更新成功")
                    .data("user", existingUser);
        }
    }
}







