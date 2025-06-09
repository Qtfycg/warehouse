package com.qtfycg.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class productController {
   /* @Resource
    productService productService;

    *//*
    * 产品列表接口
    * *//*
    @GetMapping("/list")
    public R getList() {
        // 模拟返回商品列表
        return productService.getList();
    }

    *//*
    * 产品详情接口
    * *//*
    @GetMapping("/detail")
    public R getDetail() {
        // 模拟返回商品详情
        return productService.getDetail();
    }

    *//*
    * 获取产品分类列表接口
    * *//*
    @GetMapping("/category/list")
    public R getCategoryList() {
        // 模拟返回商品分类列表
        return productService.getCategoryList();
    }*/
}
