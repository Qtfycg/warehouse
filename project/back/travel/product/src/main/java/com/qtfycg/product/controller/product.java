package com.qtfycg.product.controller;

import com.qtfycg.common.utils.R.R;
import com.qtfycg.product.Vo.saveProduct;
import com.qtfycg.product.mapper.product_flightMapper;
import com.qtfycg.product.service.productService;
import com.qtfycg.product.service.product_hotelService;
import com.qtfycg.product.service.product_trainService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class product {
    @Resource
    productService productService;
    @Resource
    product_trainService trainService;
    @Resource
    product_hotelService hotelService;
    @Resource
    product_flightMapper flightMapper;
    /*
    * 新增商品
    * */
    @RequestMapping("/add")
    public R addProduct(@RequestBody saveProduct saveProduct) {
        // 调用service层的添加商品方法
        boolean result = productService.add(saveProduct);
        if (result){
            return R.ok().message("商品添加成功").data("product", saveProduct);
        }
        return R.error().message("商品已存在，添加失败");
    }
}
