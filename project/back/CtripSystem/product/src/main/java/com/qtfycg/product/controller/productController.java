package com.qtfycg.product.controller;

import com.qtfycg.common.R.R;
import com.qtfycg.product.service.productService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class productController {
    @Resource
    productService productService;

    /*
    * 产品列表接口
    * */
    @GetMapping("/list")
    public R getList(Integer page, Integer size, String keyword) {
        // 模拟返回商品列表
        return productService.getList(page, size, keyword);
    }

    /*
    * 获取产品详情
    * */
    @GetMapping("/detail")
    public R getDetail(Long id) {
        // 模拟返回商品详情
        return productService.getDetail(id);
    }

}
