package com.qtfycg.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtfycg.admin.domain.entity.product;
import com.qtfycg.admin.domain.entity.user;
import com.qtfycg.admin.mapper.adminMapper;
import com.qtfycg.admin.mapper.productMapper;
import com.qtfycg.admin.service.productService;
import com.qtfycg.common.R.R;
import com.qtfycg.common.annotation.Login;
import com.qtfycg.common.annotation.context.loginHolder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class productServiceImpl extends ServiceImpl<productMapper, product> implements productService {

    @Resource
    adminMapper adminMapper;

    /*
     * 产品上下架
     * */
    @Login
    @Override
    public R updateProductStatus(Long id) {
    Long userId = loginHolder.getUserId();
    user userInfo = adminMapper.selectById(userId);
    if(userInfo == null || userInfo.getRole() == 1) {
        return R.error()
                .code(501)
                .message("用户权限不足");
    } else {
        product product = this.getById(id);
        if (product == null) {
            return R.error()
                    .code(702)
                    .message("商品不存在");
        }
        product.setStatus(product.getStatus() == 1 ? 0 : 1);
        this.updateById(product);
        return R.ok()
                .code(400)
                .message("商品状态更新成功")
                .data("product", product);
    }
    }

    /*
     * 查询产品信息
     * */
    @Login
    @Override
    public R getProductInfo(Long id) {
        Long userId = loginHolder.getUserId();
        user userInfo = adminMapper.selectById(userId);
        if(userInfo == null || userInfo.getRole() == 1) {
            return R.error()
                    .code(501)
                    .message("用户权限不足");
        } else {
            product product = this.getById(id);
            if (product == null) {
                return R.error()
                        .code(702)
                        .message("商品不存在");
            }
            return R.ok()
                    .code(600)
                    .message("商品查询成功")
                    .data("product", product);
        }

    }

    @Override
    public void saveProduct(product product) {
        this.save(product);
    }
}




