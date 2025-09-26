package com.qtfycg.admin.controller;

import com.qtfycg.admin.domain.Vo.loginVo;
import com.qtfycg.admin.service.adminService;
import com.qtfycg.common.R.R;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public R info() {
        return adminService.getInfo();
    }

    /*
    * 查询用户
    * */
    @GetMapping("/user/list")
    public R getList(@RequestParam("page") int page,
                         @RequestParam("size") int size,
                         @RequestParam(value = "keyword",required = false) String keyword ) {
        return adminService.getList(page, size, keyword);
    }

    /*
    * 用户状态管理
    * */
    @PatchMapping("/user/{id}/status")
    public R userStatus(@PathVariable("id") Long id) {
        return adminService.updateUserStatus(id);
    }
}
