package com.qtfycg.admin.controller;

import com.qtfycg.admin.service.productService;
import com.qtfycg.common.R.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class productController {
    @Resource
    productService productService;

    /*
    * 产品上下架
    * */
    @PatchMapping("/product/{id}/status")
    public R productStatus(@PathVariable("id") Long id) {
        return productService.updateProductStatus(id);
    }

    /*
    * 查询产品信息
    * */
    @GetMapping("/product/{id}")
    public R productInfo(@PathVariable("id") Long id) {
        return productService.getProductInfo(id);
    }

}
