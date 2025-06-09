package com.qtfycg.admin.controller;

import com.qtfycg.admin.domain.Vo.loginVo;
import com.qtfycg.admin.service.adminService;
import com.qtfycg.common.R.R;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class adminController {
    @Resource
    adminService adminService;


    /*
    * 登录接口
    * */
    @PostMapping("/login")
    public R login(@RequestBody loginVo loginVo, HttpServletResponse response) {
        return adminService.login(loginVo,response);
    }

    /*
    * 获取管理员详细信息
    * */
    @GetMapping("/info")
    public R info(HttpServletRequest request) {
        return adminService.getInfo(request);
    }

    /*
    * 查询用户
    * */
    @GetMapping("/user/list")
    public R getList(@RequestParam("page") int page,
                         @RequestParam("size") int size,
                         @RequestParam("keyword") String keyword,
                        HttpServletRequest request) {
        return adminService.getList(page, size, keyword, request);
    }
}
