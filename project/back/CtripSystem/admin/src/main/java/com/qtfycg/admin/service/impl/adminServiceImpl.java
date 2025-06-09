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
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
                return R.error().message("该账号不是管理员账号，请使用管理员账号登录");
            }else{
                if (!user.getPassword().equals(loginVo.getPassword())) {
                    return R.error().message("密码错误，请重新输入");
                } else {
                    String token = jwtUtils.generateToken(user.getId(), user.getPhone());
                    response.setHeader("Authorization", "Bearer " + token);
                    response.setHeader("Access-Control-Expose-Headers", "Authorization"); // 允许前端获取该响应头
                    return R.ok()
                            .message("登录成功")
                            .data("admin", user)
                            .data("token", token);
                }
            }
        }
        return R.error().message("账号不存在，请先注册");
    }

    /*
    * 获取管理员信息接口
    * */
    @Override
    public R getInfo(HttpServletRequest request) {
        // 获取 Authorization 头部信息
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return R.error()
                    .message("请先登录");
        }
        // 提取 token
        String token = authHeader.replace("Bearer ", "");
        // 验证 token 并获取用户信息
        Long userId;
        try {
            userId = jwtUtils.getUserId(token);
        } catch (Exception e) {
            return R.error()
                    .message("token 无效或已过期");
        }
        // 根据用户 ID 查询用户信息
        user userInfo = baseMapper.selectById(userId);
        if (userInfo == null) {
            return R.error()
                    .message("用户不存在");
        }
        return R.ok()
                .data("user", userInfo);
    }

    @Override
    public R getList(Integer page, Integer size, String keyword,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")) {
            return R.error()
                    .message("请先登录");
        }else {
            Page<user> pageInfo = new Page<>(page, size);
            LambdaQueryWrapper<user> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(user::getRole, 1);
            if (StringUtils.hasText(keyword)) {
                wrapper.like(user::getPhone, keyword)
                        .or()
                        .like(user::getName, keyword);
            }
            Page<user> userPage = this.page(pageInfo, wrapper);
            if (userPage.getRecords().isEmpty()) {
                return R.error().message("没有找到相关用户");
            }
            return R.ok()
                    .data("users", userPage.getRecords())
                    .data("total", userPage.getTotal())
                    .data("currentPage", userPage.getCurrent())
                    .data("pageSize", userPage.getSize());
        }
    }

}




