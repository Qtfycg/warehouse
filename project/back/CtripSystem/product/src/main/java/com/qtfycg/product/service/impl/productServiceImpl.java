package com.qtfycg.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.common.R.R;
import com.qtfycg.product.domain.entity.product;
import com.qtfycg.product.mapper.productMapper;
import com.qtfycg.product.service.productService;
import org.springframework.stereotype.Service;


@Service
public class productServiceImpl extends ServiceImpl<productMapper, product> implements productService{

    /*
    * 查询商品列表
    * */
    @Override
    public R getList(Integer page, Integer size, String keyword) {
        Page<product> productPage = new Page<>(page, size);
        LambdaQueryWrapper<product> queryWrapper = new LambdaQueryWrapper<>();
        if(keyword != null && !keyword.isEmpty()) {
            queryWrapper
                    // 名称
                    .like(product::getName, keyword)
                    .or()
                    // 描述
                    .like(product::getDescription, keyword)
                    .or()
                    // 状态
                    .like(product::getStatus, keyword)
                    .or()
                    // 类型
                    .like(product::getType,keyword);
            Page<product> resultPage = this.page(productPage, queryWrapper);
            return R.ok()
                    .code(600)
                    .message("查询成功")
                    .data("products", resultPage.getRecords())
                    .data("total", resultPage.getTotal());
        }else {
            Page<product> resultPage = this.page(productPage);
            return R.ok()
                    .code(600)
                    .message("查询成功")
                    .data("products", resultPage.getRecords())
                    .data("total", resultPage.getTotal());
        }


    }

    /*
    * 获取商品详情
    * */
    @Override
    public R getDetail(Long id) {
        product product = this.getById(id);
        if(product != null) {
            return R.ok()
                    .code(600)
                    .message("查询成功")
                    .data("product", product);
        } else {
            return R.error()
                    .code(702)
                    .message("商品不存在");
        }
    }
}




