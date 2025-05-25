package com.qtfycg.product.controller;

import com.qtfycg.common.utils.R.R;
import com.qtfycg.product.Vo.saveVo;
import com.qtfycg.product.Vo.searchVo;
import com.qtfycg.product.service.productService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class product {
    @Resource
    productService productService;
    /*
    * 新增商品
    * */
    @PostMapping("/add")
    public R addProduct(@RequestBody saveVo saveVo) {
        return productService.add(saveVo);
    }

    /*
    * 产品详情
    * */
    @GetMapping("/detail")
    public R getDetailProduct(@RequestParam Long id) {
        return productService.getDetail(id);
    }

    /*
    * 分页查询
    * */
    @GetMapping("/search")
    public R search(@RequestBody searchVo searchVo) {
        return productService.searchProducts(searchVo);
    }
}
