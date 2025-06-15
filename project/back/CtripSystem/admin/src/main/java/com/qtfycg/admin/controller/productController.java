package com.qtfycg.admin.controller;

import com.qtfycg.admin.domain.Vo.createVo;
import com.qtfycg.admin.service.productService;
import com.qtfycg.common.R.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/product")
public class productController {
    @Resource
    productService productService;

    /*
    * 新增产品
    * */
    @PostMapping("/create")
    public R productCreate(@RequestBody createVo createVo) {
        return productService.createProduct(createVo);
    }

    /*
    * 修改产品信息
    * */
}
