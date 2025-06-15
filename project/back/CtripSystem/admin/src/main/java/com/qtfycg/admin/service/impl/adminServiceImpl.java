package com.qtfycg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.admin.domain.Vo.loginVo;
import com.qtfycg.admin.domain.entity.user;
import com.qtfycg.admin.mapper.adminMapper;
import com.qtfycg.admin.service.adminService;
import com.qtfycg.common.JWT.jwtUtils;
import com.qtfycg.common.R.R;
import com.qtfycg.common.annotation.Login;
import com.qtfycg.common.annotation.context.loginHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class adminServiceImpl extends ServiceImpl<adminMapper, user> implements adminService {

    @Resource
    jwtUtils jwtUtils;

    /*
    * role：1--用户、0--管理员
    * */
    @Override
    public R login(loginVo loginVo, HttpServletResponse response) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", loginVo.getPhone());
        user user = this.getOne(queryWrapper);
        if (user != null) {
            if(user.getRole() == 1){
                return R.error()
                        .code(500)
                        .message("该账号不是管理员账号，请使用管理员账号登录");
            }else{
                if (!user.getPassword().equals(loginVo.getPassword())) {
                    return R.error()
                            .code(500)
                            .message("密码错误，请重新输入");
                } else {
                    String token = jwtUtils.generateToken(user.getId(), user.getPhone());
                    response.setHeader("Authorization", "Bearer " + token);
                    response.setHeader("Access-Control-Expose-Headers", "Authorization"); // 允许前端获取该响应头
                    return R.ok()
                            .code(400)
                            .message("登录成功")
                            .data("admin", user)
                            .data("token", token);
                }
            }
        }
        return R.error()
                .code(500)
                .message("账号不存在，请先注册");
    }

    /*
    * 获取管理员信息接口
    * */
    @Login
    @Override
    public R getInfo() {
        Long userId = loginHolder.getUserId();
        // 根据用户 ID 查询用户信息
        user userInfo = baseMapper.selectById(userId);
        if (userInfo == null) {
            return R.error()
                    .code(500)
                    .message("用户不存在");
        }
        return R.ok()
                .code(400)
                .message("获取用户信息成功")
                .data("user", userInfo);
    }

    /*
    * 获取用户列表接口
    * */
    @Login
    @Override
    public R getList(Integer page, Integer size, String keyword) {
        Long userId = loginHolder.getUserId();
        user getVo = baseMapper.selectById(userId);
        if (getVo == null) {
            return R.error()
                    .code(500)
                    .message("账号不存在，请先登录");
        }else {
            if( getVo.getRole() == 1) {
                return R.error()
                        .code(500)
                        .message("该账号不是管理员账号，请使用管理员账号登录");
            } else {
                // 分页查询用户列表
                Page<user> userPage = new Page<>(page, size);
                LambdaQueryWrapper<user> queryWrapper = new LambdaQueryWrapper<>();
                if (StringUtils.hasText(keyword)) {
                    queryWrapper.like(user::getName, keyword)
                            .or()
                            .like(user::getPhone, keyword)
                            .or()
                            .like(user::getEmail, keyword);
                }
                Page<user> resultPage = this.page(userPage, queryWrapper);
                return R.ok()
                        .code(400)
                        .message("获取用户列表成功")
                        .data("users", resultPage.getRecords())
                        .data("total", resultPage.getTotal());
            }

        }
    }

}




