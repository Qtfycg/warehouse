package com.qtfycg.admin.controller;

import com.qtfycg.admin.domain.Vo.createProductVo;
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
    * 新增产品
    * */
    @PostMapping("/product/create")
    public R productCreate(@RequestBody createProductVo createProductVo) {
        return productService.createProduct(createProductVo);
    }

    /*
    * 修改产品信息
    * PUT /api/admin/product/{id}
    * */
    @PutMapping("/product/{id}/update")
    public R productUpdate(@PathVariable("id") Long id, @RequestBody createProductVo createProductVo) {
        createProductVo.setId(id);
        return productService.updateProduct(createProductVo);
    }

    /*
    * 产品上下架
    * */
    @PatchMapping("/product/{id}/status")
    public R productStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status) {
        return productService.updateProductStatus(id, status);
    }

    /*
    * 查询产品信息
    * */
    @GetMapping("/product/{id}")
    public R productInfo(@PathVariable("id") Long id) {
        return productService.getProductInfo(id);
    }

}
